package com.movie_rating.api.controller;

import com.movie_rating.api.model.dto.ApiModelDTO;
import com.movie_rating.api.model.entity.MovieApiModel;
import com.movie_rating.api.service.TmbdService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/tmdb")
public class TmdbController {
    private final TmbdService tmbdService;

    public TmdbController(TmbdService tmbdService) {
        this.tmbdService = tmbdService;
    }

    @GetMapping
    public Mono<ResponseEntity<String>> getMoviesTmbd(@RequestParam(defaultValue = "1") int page) {
        return tmbdService.processAndSaveMovies(page)
                .thenReturn(ResponseEntity.ok("Movies saved successfully"));
    }
}
