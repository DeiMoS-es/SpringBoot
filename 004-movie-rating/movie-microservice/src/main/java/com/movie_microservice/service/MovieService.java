package com.movie_microservice.service;

import com.movie_microservice.models.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MovieService {
    ResponseEntity<String> saveAllMovies(List<Movie> movies);
    Page<Movie> findTenMovies(Pageable pageable);
}
