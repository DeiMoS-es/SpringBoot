package com.movie_rating.api.service;

import com.movie_rating.api.model.dto.ApiModelDTO;
import com.movie_rating.api.model.entity.MovieApiModel;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TmbdService {
    Mono<List<ApiModelDTO>> getMoviesTmbd(int page);
    Mono<Void> saveOneMovieTmdb(MovieApiModel movie);
}
