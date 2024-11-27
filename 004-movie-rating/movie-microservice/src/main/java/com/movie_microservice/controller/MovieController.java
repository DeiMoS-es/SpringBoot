package com.movie_microservice.controller;

import com.movie_microservice.models.entity.Movie;
import com.movie_microservice.service.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private PagedResourcesAssembler<Movie> pagedResourcesAssembler;

    @GetMapping("/list")
    public ResponseEntity<Page<Movie>> listMovies(@RequestParam (defaultValue = "0") int page) {
        // Pageable es una interfaz en Spring Data que se utiliza para la paginación.
        // Define información sobre la página que se desea recuperar, como el número de página y el tamaño de la página.
        Pageable pageable = PageRequest.of(page, 10, Sort.by("movieId").ascending()); // Página 0, tamaño de página 10
        Page<Movie> movies = movieService.findTenMovies(pageable);
        PagedModel<EntityModel<Movie>> pagedModel = pagedResourcesAssembler.toModel(movies);
        return  ResponseEntity.ok(movies);
    }
}
