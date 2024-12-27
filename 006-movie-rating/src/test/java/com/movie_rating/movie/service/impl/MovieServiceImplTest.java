package com.movie_rating.movie.service.impl;

import com.movie_rating.movie.model.dto.MovieDTO;
import com.movie_rating.movie.model.dto.MovieResponsePaginated;
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
    void testGetMoviesPageable() {
        // 1. Crea un objeto Pageable con la configuración de la página (número de página y tamaño de página).
        Pageable pageable = PageRequest.of(0, 10);
        // 2. Crea una página de películas con una lista que contiene una película de prueba.
        Page<Movie> moviePage = new PageImpl<>(List.of(movie), pageable, 1);
        // 3. Configura el comportamiento del mock del repositorio para que devuelva la página de películas cuando se le llame con el objeto Pageable.
        when(movieRepository.findAll(pageable)).thenReturn(moviePage);
        // 4. Configura el comportamiento del mock del ModelMapper para que convierta la entidad Movie a un DTO MovieDTO.
        when(modelMapper.map(movie, MovieDTO.class)).thenReturn(movieDTO);
        // 5. Llama al método getMoviesPageable del servicio y guarda el resultado en un Mono.
        Mono<MovieResponsePaginated<MovieDTO>> result = movieService.getMoviesPageable(pageable);
        // 6. Verifica el resultado del Mono usando StepVerifier.
        StepVerifier.create(result)
                // 7. Verifica que el contenido y la información de paginación del resultado sean correctos.
                .expectNextMatches(response -> {
                    boolean isContentCorrect = response.getContent().size() == 1 &&
                            response.getContent().get(0).getTitle().equals("Test Movie");
                    boolean isPageableCorrect = response.getPage() == 0 &&
                            response.getTotalPages() == 1 &&
                            response.getTotalResults() == 1 &&
                            response.isLast() &&
                            response.isFirst();
                    return isContentCorrect && isPageableCorrect;
                })
                // 8. Verifica que el Mono se complete correctamente.
                .verifyComplete();
    }
}
