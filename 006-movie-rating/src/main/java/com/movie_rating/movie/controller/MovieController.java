package com.movie_rating.movie.controller;

import com.movie_rating.movie.model.dto.MovieDTO;
import com.movie_rating.movie.model.dto.MovieResponsePaginated;
import com.movie_rating.movie.service.MovieService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }
    /**
     * Método que devuelve un objeto paginado con el total de páginas, página actual.
     * El método devolverá un Mono que, cuando se complete, emitirá una instancia de MovieResponsePaginated que contiene objetos de tipo MovieDTO.
     */
    @GetMapping
    public Mono<ResponseEntity<MovieResponsePaginated<MovieDTO>>> getMoviesPaginnated(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "20") int size){
        Pageable pageable = PageRequest.of(page, size);
        return movieService.getMoviesPageable(pageable)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<String>> saveMovie(@RequestBody MovieDTO movieDTO){
        return movieService.saveMovie(movieDTO)
                .thenReturn(ResponseEntity.ok("Movie saved successfully"));
    }
}
