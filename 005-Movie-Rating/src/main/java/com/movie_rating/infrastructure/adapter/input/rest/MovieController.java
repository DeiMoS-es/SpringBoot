package com.movie_rating.infrastructure.adapter.input.rest;

import com.movie_rating.aplication.dto.MovieTmbdDTO;
import com.movie_rating.domain.port.input.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }
    //@GetMapping("/tmbd")
   // public Mono<List<MovieTmbdDTO>> getMoviesTmbd(){
       // return movieService.getMoviesTmbd();
    //}
}
