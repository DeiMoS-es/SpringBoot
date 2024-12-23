package com.movie_rating.api.service.impl;

import com.movie_rating.api.client.TmdbClient;
import com.movie_rating.api.model.dto.ApiModelDTO;
import com.movie_rating.api.model.dto.PaginatedResponseDTO;
import com.movie_rating.api.model.entity.GenreApiModel;
import com.movie_rating.api.model.entity.MovieApiModel;
import com.movie_rating.api.repository.GenreRepository;
import com.movie_rating.api.repository.TmdbRepository;
import com.movie_rating.api.service.TmbdService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.Optional;

@Service
public class TmdbServiceImpl implements TmbdService {
    private final TmdbRepository tmdbRepository;
    private final GenreRepository genreRepository;
    private final TmdbClient tmdbClient;

    public TmdbServiceImpl(TmdbRepository tmdbRepository, TmdbClient tmdbClient, GenreRepository genreRepository) {
        this.tmdbRepository = tmdbRepository;
        this.genreRepository = genreRepository;
        this.tmdbClient = tmdbClient;
    }
    @Override
    public Mono<List<ApiModelDTO>> getMoviesTmbd(int page) {
        return tmdbClient.getMovies(page);
    }

    @Override
    public Mono<Void> saveMovieTmdb(ApiModelDTO moviesDTO) {
       return Mono.fromRunnable(() -> {
           // Mapear IDs de géneros a objetos GenreApiModel
              List<GenreApiModel> genres = moviesDTO.getGenreIds().stream()
                     .map(this::getOrCreateGenre)
                     .toList();
              // Convertir DTO a entidad
                MovieApiModel movie = moviesDTO.toEntity(genres);
                // Guardar la entidad
                tmdbRepository.save(movie);
       });
    }

    @Override
    public List<GenreApiModel> mapGenres(List<Integer> genreIds) {
        return genreIds.stream()
                .map(this::getOrCreateGenre)
                .toList();
    }

    @Override
    public Mono<List<MovieApiModel>> processAndSaveMovies(int page) {
        return getMoviesTmbd(page)
                .flatMapMany(Flux::fromIterable)
                .flatMap(movieDTO -> {
                    return saveMovieTmdb(movieDTO)
                            .thenReturn(movieDTO.toEntity(mapGenres(movieDTO.getGenreIds())));
                })
                .collectList();
    }

    @Override
    public Mono<Page<ApiModelDTO>> getMoviesTmbdPage(Pageable pageable) {
        int apiPage = pageable.getPageNumber() + 1; // Ajustar para la API
        return tmdbClient.getMovies(apiPage)
                .map(movies -> new PageImpl<>(
                        movies,                 // Lista de películas
                        pageable,               // Información de paginación
                        movies.size()           // Número total de resultados (provisional)
                ));
    }
    /**
     * Obtiene películas de TMDb y devuelve una respuesta paginada.
     *
     * @param pageable la información de paginación proporcionada por Spring Data
     * @return una respuesta paginada con las películas y metadatos
     */
    public Mono<PaginatedResponseDTO<ApiModelDTO>> getMoviesTmbdPageable(Pageable pageable) {
        int currentPage = pageable.getPageNumber() + 1; // TMDb usa índices basados en 1

        return tmdbClient.getMoviesPruebaPageable(currentPage)
                .map(response -> new PaginatedResponseDTO<>(
                        response.getResults(),
                        pageable.getPageNumber(),
                        response.getTotalPages(),
                        response.getTotalResults(),
                        pageable.getPageNumber() == response.getTotalPages() - 1,
                        pageable.getPageNumber() == 0
                ));
    }

    // Método para obtener un género de la base de datos o crear uno nuevo si no existe
    private GenreApiModel getOrCreateGenre(Integer genreId){
        Optional<GenreApiModel> optionalGenre = genreRepository.findByGenreId(genreId);
        return optionalGenre.orElseGet( () -> {
            GenreApiModel genre = new GenreApiModel();
            genre.setGenreId(genreId);
            return genreRepository.save(genre);
                });
    }
}