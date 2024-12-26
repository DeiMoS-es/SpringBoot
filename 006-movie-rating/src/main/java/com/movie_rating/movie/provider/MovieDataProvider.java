package com.movie_rating.movie.provider;

import com.movie_rating.api.model.entity.MovieApiModel;

import java.util.List;

/**
 * Clase que define el contrato para obtener datos de películas.
 */
public interface MovieDataProvider {
    List<MovieApiModel> getAllmovies();
    //Optional<Movie> getMovieById(Long movieId);
}
