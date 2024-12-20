package com.movie_rating.infrastructure.adapter.input.rest;

import com.movie_rating.aplication.dto.MovieTmbdDTO;
import com.movie_rating.domain.port.input.MovieTmbdService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/tmbd")
public class TmbdController {

    private final MovieTmbdService movieTmbdService;

    public TmbdController(MovieTmbdService movieTmbdService){
        this.movieTmbdService = movieTmbdService;
    }

    @GetMapping
    public Mono<List<MovieTmbdDTO>> getMoviesTmbd(@RequestParam(defaultValue = "1") int page){
        return movieTmbdService.getMoviesTmbd(page);
    }
}
