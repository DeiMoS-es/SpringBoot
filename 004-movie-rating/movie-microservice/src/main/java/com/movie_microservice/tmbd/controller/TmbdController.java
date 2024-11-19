package com.movie_microservice.tmbd.controller;

import com.movie_microservice.models.entity.Movie;
import com.movie_microservice.service.MovieService;
import com.movie_microservice.tmbd.service.TmbdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/tmbd")
public class TmbdController {

    @Autowired
    private TmbdService tmbdService;
    @Autowired
    private MovieService movieService;

    @GetMapping("/movies")
    public Mono<List<Movie>> getAllMovies(@RequestParam(defaultValue = "1") int page){
        return this.tmbdService.getAllMovies(page);
    }
    @PostMapping("/movies/saveAll")
    public ResponseEntity<String> saveAllMovies(@RequestParam(defaultValue = "1") int page){
        List<Movie> movies = this.tmbdService.getAllMovies(page).block();
        return this.movieService.saveAllMovies(movies);
    }
}
