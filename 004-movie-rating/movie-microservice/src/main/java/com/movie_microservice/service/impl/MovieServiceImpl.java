package com.movie_microservice.service.impl;

import com.movie_microservice.models.entity.Movie;
import com.movie_microservice.repository.MovieRepository;
import com.movie_microservice.service.MovieService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public ResponseEntity<String> saveAllMovies(List<Movie> movies) {
        log.info("Saving movies");
        try {
            movieRepository.saveAll(movies);
            return ResponseEntity.ok("Movies saved successfully");
        } catch (Exception e) {
            log.error("Error saving movies", e);
            return ResponseEntity.badRequest().body("Error saving movies");
        }
    }

    @Override
    public Page<Movie> findTenMovies(Pageable pageable) {
        return movieRepository.findAll(pageable);
    }

    @Transactional
    public void saveAllMoviesInBatch(List<Movie> movies) { movieRepository.saveAll(movies);}

    public boolean movieExists(int id) {
        return movieRepository.existsById(id);
    }
}
