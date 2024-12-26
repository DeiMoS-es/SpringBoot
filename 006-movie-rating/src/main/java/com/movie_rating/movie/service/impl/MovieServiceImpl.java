package com.movie_rating.movie.service.impl;

import com.movie_rating.movie.config.ModelMapperConfig;
import com.movie_rating.movie.model.dto.MovieDTO;
import com.movie_rating.movie.model.dto.MovieResponsePaginated;
import com.movie_rating.movie.model.entity.Genre;
import com.movie_rating.movie.model.entity.Movie;
import com.movie_rating.movie.repository.GenreRepository;
import com.movie_rating.movie.repository.MovieRepository;
import com.movie_rating.movie.service.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;
    private GenreRepository genreRepository;

    public MovieServiceImpl(MovieRepository movieRepository, ModelMapper modelMapper, GenreRepository genreRepository) {
        this.movieRepository = movieRepository;
        this.modelMapper = modelMapper;
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Mono<MovieResponsePaginated<MovieDTO>> getMoviesPageable(Pageable pageable) {
        return Mono.fromCallable(() -> movieRepository.findAll(pageable))
                .map( page -> {
                    List<MovieDTO> movieDTOs = page.getContent().stream()
                            .map(this::mapToDTO)
                            .toList();
                    return new MovieResponsePaginated<>(
                            movieDTOs,
                            page.getNumber(),
                            page.getTotalPages(),
                            page.getTotalElements(),
                            page.isLast(),
                            page.isFirst()
                    );
                });
    }
    private MovieDTO mapToDTO(Movie movie) {
        return modelMapper.map(movie, MovieDTO.class);
    }
    private Movie mapToEntity(MovieDTO movieDTO, List<Genre> genres) {
        Movie movie = modelMapper.map(movieDTO, Movie.class);
        movie.setDescription(movieDTO.getOverview());
        movie.setGenres(genres);
        return movie;
    }

    @Override
    public Mono<Void> saveMovie(MovieDTO movieDTO) {
        return Mono.fromRunnable(() -> {
            List<Genre> genres = movieDTO.getGenreIds().stream()
                    .map(this:: getOrCreateGenre)
                    .toList();
            Movie movie = mapToEntity(movieDTO, genres);
            movie.setCreatedAt(new Date());
            movieRepository.save(movie);
        });
    }
    private Genre getOrCreateGenre(Integer genreId) {
        Optional<Genre> optionalGenre = genreRepository.findByGenreId(genreId);
        return optionalGenre.orElseGet(() ->{
            Genre genre = new Genre();
            genre.setGenreId(genreId);
            return genreRepository.save(genre);
        });
    }
    @Override
    public Mono<Void> saveMovies(List<MovieDTO> movieDTO) {
        return null;
    }

    @Override
    public Mono<MovieDTO> getMovieById(Long id) {
        return null;
    }

    @Override
    public Mono<MovieDTO> getMovieByTitle(String title) {
        return null;
    }

    @Override
    public Mono<Void> deleteMovieById(Long id) {
        return null;
    }

    @Override
    public Mono<Void> deleteMovieByTitle(String title) {
        return null;
    }

    @Override
    public Mono<Void> updateMovie(MovieDTO movieDTO) {
        return null;
    }
}
