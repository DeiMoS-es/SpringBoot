package com.movie_microservice.service;

import com.movie_microservice.models.entity.Movie;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MovieService {
    public ResponseEntity<String> saveAllMovies(List<Movie> movies);
}
