package com.movie_rating.api.service.impl;

import com.movie_rating.api.client.TmdbClient;
import com.movie_rating.api.model.dto.ApiModelDTO;
import com.movie_rating.api.model.entity.MovieApiModel;
import com.movie_rating.api.repository.GenreTmdbRepository;
import com.movie_rating.api.repository.TmdbRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyInt;

import java.util.List;

public class TmdbServiceImplTest {
    @Mock
    private TmdbRepository tmdbRepository;
    @Mock
    private GenreTmdbRepository genreTmdbRepository;
    @Mock
    private TmdbClient tmdbClient;
    @InjectMocks
    private TmdbServiceImpl tmdbService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }
     @Test
    public void testProcessAndSaveMovies(){
        // Config datos de prueba
         ApiModelDTO movieDTO = new ApiModelDTO();
            movieDTO.setId(1);
            movieDTO.setTitle("Test Movie");
            movieDTO.setGenreIds(List.of(1, 2, 3));

         MovieApiModel movie = new MovieApiModel();
            movie.setMovieId(1);
            movie.setTitle("Test Movie");
            movie.setGenreIds(List.of(1, 2, 3));
         // Config de mocks
         when(tmdbClient.getMovies(anyInt())).thenReturn(Mono.just(List.of(movieDTO)));
         when(tmdbRepository.save(movie)).thenReturn(movie);

         // Ejecutar método a probar
         List<MovieApiModel> result = tmdbService.processAndSaveMovies(1).block();
         // Verificar el resultado
         assertEquals(1, result.size());
         assertEquals("Test Movie", result.get(0).getTitle());
     }

}
