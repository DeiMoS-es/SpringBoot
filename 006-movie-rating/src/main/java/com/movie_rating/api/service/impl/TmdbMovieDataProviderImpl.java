package com.movie_rating.api.service.impl;

import com.movie_rating.api.model.entity.MovieApiModel;
import com.movie_rating.api.repository.TmdbRepository;
import com.movie_rating.movie.provider.MovieDataProvider;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Clase que implementa el contrato para obtener datos de películas.
 */
@Service
public class TmdbMovieDataProviderImpl implements MovieDataProvider {
    private final TmdbRepository tmdbRepository;

    public TmdbMovieDataProviderImpl(TmdbRepository tmdbRepository) {
        this.tmdbRepository = tmdbRepository;
    }
    @Override
    public List<MovieApiModel> getAllmovies() {
        return tmdbRepository.findAll();
    }
}
