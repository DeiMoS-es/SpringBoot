package com.movie_rating.infrastructure.adapter.controller;

import com.movie_rating.aplication.port.output.MovieService;
import com.movie_rating.domain.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;
    private final PagedResourcesAssembler<Movie> pagedResourcesAssembler;
    public MovieController(MovieService movieService, PagedResourcesAssembler<Movie> pagedResourcesAssembler) {
        this.movieService = movieService;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    /**
     * Método que se encarga de listar las películas.
     * @param page Número de página, por defecto es 0.
     * @return Este método devuelve una respuesta HTTP que contiene un modelo paginado de entidades de tipo Movie.
     */
    @GetMapping("/list")
    public ResponseEntity<PagedModel<EntityModel<Movie>>> listMovies(@RequestParam (defaultValue = "0") int page) {
        // Pageable es una interfaz en Spring Data que se utiliza para la paginación.
        // Define información sobre la página que se desea recuperar, como el número de página y el tamaño de la página.
        Pageable pageable = PageRequest.of(page, 10, Sort.by("movieId").ascending()); // Página 0, tamaño de página 10
        Page<Movie> movies = movieService.findTenMovies(pageable);
        // Se utiliza PagedResourcesAssembler para convertir la página de películas en un PagedModel que incluye enlaces de navegación para la paginación.
        PagedModel<EntityModel<Movie>> pagedModel = pagedResourcesAssembler.toModel(movies);
        return  ResponseEntity.ok(pagedModel);
    }
}
