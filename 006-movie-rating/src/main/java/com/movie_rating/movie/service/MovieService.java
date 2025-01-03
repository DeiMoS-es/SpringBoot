package com.movie_rating.movie.service;

import com.movie_rating.movie.model.dto.MovieDTO;
import com.movie_rating.movie.model.entity.Movie;
import reactor.core.publisher.Mono;

import java.util.List;

public interface MovieService {
    List<Movie>findAll();
    //Mono<MovieResponsePaginated<MovieDTO>> getMoviesPageable(Pageable pageable);
    Mono<Void> saveMovie(MovieDTO movieDTO);
    Mono<Void> saveMovies(List<MovieDTO> movieDTO);
    Mono<Void> updateMovieDataBase();
    Mono<MovieDTO> getMovieById(Long id);
    Mono<MovieDTO> getMovieByTitle(String title);
    Mono<Void> deleteMovieById(Long id);
    Mono<Void> deleteMovieByTitle(String title);
    Mono<Void> updateMovie(MovieDTO movieDTO);
    List<MovieDTO> getMovies(int page);
}
