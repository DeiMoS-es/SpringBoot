package com.movie_microservice.tmbd.service;

import ch.qos.logback.core.rolling.helper.MonoTypedConverter;
import com.movie_microservice.models.entity.Movie;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TmbdService {
    Mono<List<Movie>> getAllMovies(int page);
    Mono<Integer> getTotalPages();
}
