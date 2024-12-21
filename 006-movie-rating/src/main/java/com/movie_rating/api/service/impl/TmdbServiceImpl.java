package com.movie_rating.api.service.impl;

import com.movie_rating.api.client.TmdbClient;
import com.movie_rating.api.model.dto.ApiModelDTO;
import com.movie_rating.api.model.entity.GenreApiModel;
import com.movie_rating.api.model.entity.MovieApiModel;
import com.movie_rating.api.repository.GenreRepository;
import com.movie_rating.api.repository.TmdbRepository;
import com.movie_rating.api.service.TmbdService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
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