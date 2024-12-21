package com.movie_rating.api.service.impl;

import com.movie_rating.api.client.TmdbClient;
import com.movie_rating.api.model.dto.ApiModelDTO;
import com.movie_rating.api.model.entity.MovieApiModel;
import com.movie_rating.api.repository.TmdbRepository;
import com.movie_rating.api.service.TmbdService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@Service
public class TmdbServiceImpl implements TmbdService {
    private final TmdbRepository tmdbRepository;
    private final TmdbClient tmdbClient;

    public TmdbServiceImpl(TmdbRepository tmdbRepository, TmdbClient tmdbClient) {
        this.tmdbRepository = tmdbRepository;
        this.tmdbClient = tmdbClient;
    }
    @Override
    public Mono<List<ApiModelDTO>> getMoviesTmbd(int page) {
        return tmdbClient.getMovies(page);
    }

    @Override
    public Mono<Void> saveOneMovieTmdb(MovieApiModel movie) {
        return Mono.fromRunnable(() -> tmdbRepository.save(movie));
    }
}
