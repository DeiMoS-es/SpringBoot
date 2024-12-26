package com.movie_rating.movie.provider;

import com.movie_rating.api.model.entity.MovieApiModel;

import java.util.List;
import java.util.Optional;

public interface MovieDataProvider {
    List<MovieApiModel> getAllmovies();
    //Optional<Movie> getMovieById(Long movieId);
}
