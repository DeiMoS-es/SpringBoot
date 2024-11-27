package com.movie_microservice.tmbd.controller;

import com.movie_microservice.models.entity.Movie;
import com.movie_microservice.service.MovieService;
import com.movie_microservice.service.impl.MovieServiceImpl;
import com.movie_microservice.tmbd.service.TmbdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tmbd")
public class TmbdController {

    @Autowired
    private TmbdService tmbdService;
    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieServiceImpl movieServiceImpl;

    @GetMapping("/movies")
    public Mono<List<Movie>> getAllMovies(@RequestParam(defaultValue = "1") int page){
        return this.tmbdService.getAllMovies(page);
    }
    @PostMapping("/movies/saveAll")
    public ResponseEntity<String> saveAllMovies(@RequestParam(defaultValue = "1") int page){
        List<Movie> movies = this.tmbdService.getAllMovies(page).block();
        String totalPages = this.tmbdService.getTotalPages().block().toString();
        System.out.println("Total pages: " + totalPages);
        return this.movieService.saveAllMovies(movies);
    }

    @PostMapping("/movies/saveAllPages/V2")
    public ResponseEntity<String> saveAllMoviesV2() {
        int totalPages = this.tmbdService.getTotalPages().block();
        ExecutorService executor = Executors.newFixedThreadPool(10); // Pool de hilos con 10 hilos
        for (int i = 1; i <= totalPages; i++) {
            int page = i;
            executor.submit(() -> {
                List<Movie> movies = this.tmbdService.getAllMovies(page).block();
                List<Movie> moviesToSave = movies.stream()
                                                 .filter(movie -> !this.movieServiceImpl.movieExists(movie.getMovieId()))
                                                 .collect(Collectors.toList());
                this.movieServiceImpl.saveAllMoviesInBatch(moviesToSave);
            });
            try {
                Thread.sleep(2000); // Retardo de 4 segundos
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return ResponseEntity.status(500).body("Error during sleep: " + e.getMessage());
            }
        }
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            return ResponseEntity.status(500).body("Error during executor shutdown: " + e.getMessage());
        }
        return ResponseEntity.ok("Movies saved");
    }

}
