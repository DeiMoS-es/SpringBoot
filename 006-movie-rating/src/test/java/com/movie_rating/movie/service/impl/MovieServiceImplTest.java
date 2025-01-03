package com.movie_rating.movie.service.impl;

import com.movie_rating.movie.model.dto.MovieDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.movie_rating.movie.model.entity.Movie;
import com.movie_rating.movie.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class MovieServiceImplTest {
    @Mock
    private MovieRepository movieRepository;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private MovieServiceImpl movieService;
    private Movie movie;
    private MovieDTO movieDTO;

    @BeforeEach
    void setUp() {
        movie = new Movie();
        movie.setMovieId(1L);
        movie.setTitle("Test Movie");
        movieDTO = new MovieDTO();
        movieDTO.setTitle("Test Movie");
    }
    @Test
    void testGetMovieById(){
        // 1. Configura el comportamiento del mock del repositorio para que devuelva una película cuando se le llame con el ID de la película.
        when(movieRepository.findById(movie.getMovieId())).thenReturn(Optional.of(movie));
        // 2. Configura el comportamiento del mock del ModelMapper para que convierta la entidad Movie a un DTO MovieDTO.
        when(modelMapper.map(movie, MovieDTO.class)).thenReturn(movieDTO);
        // 3. Llama al método getMovieById del servicio y guarda el resultado en un Mono.
        Mono<MovieDTO> result = movieService.getMovieById(movie.getMovieId());
        // 4. Verifica el resultado del Mono usando StepVerifier.
        StepVerifier.create(result)
                // 5. Verifica que el contenido del resultado sea correcto.
                .expectNextMatches(response -> response.getTitle().equals("Test Movie"))
                // 6. Verifica que el Mono se complete correctamente.
                .verifyComplete();
    }
    @Test
    void testDeleteMovieById() {
    // 1. Configura el comportamiento del mock del repositorio para que no haga nada cuando se le llame con el ID de la película.
    doNothing().when(movieRepository).deleteById(movie.getMovieId());
    // 2. Llama al método deleteMovieById del servicio y guarda el resultado en un Mono.
    Mono<Void> result = movieService.deleteMovieById(movie.getMovieId());
    // 3. Verifica que el Mono se complete correctamente.
    StepVerifier.create(result)
            .verifyComplete();
}
    @Test
    void testGetMovies() {
        // 1. Configura el número de página.
        int page = 0;
        // 2. Crea un objeto Pageable con la configuración de la página (número de página y tamaño de página).
        Pageable pageable = PageRequest.of(page, 10);
        // 3. Crea una página de películas con una lista que contiene una película de prueba.
        Page<Movie> moviePage = new PageImpl<>(List.of(movie), pageable, 1);
        // 4. Configura el comportamiento del mock del repositorio para que devuelva la página de películas cuando se le llame con el objeto Pageable.
        when(movieRepository.findAll(pageable)).thenReturn(moviePage);
        // 5. Configura el comportamiento del mock del ModelMapper para que convierta la entidad Movie a un DTO MovieDTO.
        when(modelMapper.map(movie, MovieDTO.class)).thenReturn(movieDTO);
        // 6. Llama al método getMoviesPageable del servicio y guarda el resultado en una lista.
        List<MovieDTO> result = movieService.getMovies(page);
        // 7. Verifica que el contenido del resultado sea correcto.
        assertEquals(1, result.size());
        assertEquals("Test Movie", result.get(0).getTitle());
    }
}
