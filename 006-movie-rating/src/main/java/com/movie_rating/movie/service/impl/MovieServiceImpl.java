package com.movie_rating.movie.service.impl;

import com.movie_rating.movie.config.ModelMapperConfig;
import com.movie_rating.movie.model.dto.MovieDTO;
import com.movie_rating.movie.model.dto.MovieResponsePaginated;
import com.movie_rating.movie.model.entity.Movie;
import com.movie_rating.movie.repository.MovieRepository;
import com.movie_rating.movie.service.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;

    public MovieServiceImpl(MovieRepository movieRepository, ModelMapper modelMapper) {
        this.movieRepository = movieRepository;
        this.modelMapper = modelMapper;
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
    private Movie mapToEntity(MovieDTO movieDTO) {
        return modelMapper.map(movieDTO, Movie.class);
    }

    @Override
    public Mono<Void> saveMovie(MovieDTO movieDTO) {
        return null;
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
