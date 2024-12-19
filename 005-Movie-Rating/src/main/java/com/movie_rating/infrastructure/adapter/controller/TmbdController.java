package com.movie_rating.infrastructure.adapter.controller;

import com.movie_rating.aplication.port.output.TmbdService;
import com.movie_rating.domain.model.Movie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/tmbd")
public class TmbdController {
    private final TmbdService tmbdService;

    public TmbdController(TmbdService tmbdService) {
        this.tmbdService = tmbdService;
    }

    @GetMapping("/movies")
    public Mono<List<Movie>> getMovies() {
        return tmbdService.getMovies(1);
    }
}
