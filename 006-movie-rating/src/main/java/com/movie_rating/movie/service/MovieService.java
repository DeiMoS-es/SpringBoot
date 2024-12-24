package com.movie_rating.movie.service;

import com.movie_rating.movie.model.entity.Movie;
import reactor.core.publisher.Mono;

import java.util.List;

public interface MovieService {
    List<Movie>findAll();
    Mono<PageableRe>
}
