package com.movie_rating.api.controller;

import com.movie_rating.api.model.dto.ApiModelDTO;
import com.movie_rating.api.model.entity.MovieApiModel;
import com.movie_rating.api.service.TmbdService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tmdb")
public class TmdbController {
    private final TmbdService tmbdService;

    public TmdbController(TmbdService tmbdService) {
        this.tmbdService = tmbdService;
    }
//TODO revisar porque no alamacena los generos.
    // TODO validar que los ids no se repitan ni de las películas ni de los géneros
    @GetMapping
    public Mono<List<MovieApiModel>> getMoviesTmbd(@RequestParam(defaultValue = "1") int page) {
        Mono<List<ApiModelDTO>> movies = tmbdService.getMoviesTmbd(page);
        // Convertir la lista de DTOs a una lista de entidades y guardarlas de manera reactiva
        return movies.flatMapMany(Flux::fromIterable)
                .map(ApiModelDTO::toEntity)
                .flatMap(movie -> tmbdService.saveOneMovieTmdb(movie).thenReturn(movie))
                .collectList();
    }
}
