package com.movie_microservice.tmbd.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie_microservice.models.entity.Genre;
import com.movie_microservice.models.entity.Movie;
import com.movie_microservice.repository.GenreRepository;
import com.movie_microservice.tmbd.service.TmbdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TmbdServiceImpl  implements TmbdService {

    private final WebClient webClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private GenreRepository genreRepository;

    @Value("${TMDB_API_KEY}")
    private String apiKey;

    public TmbdServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.themoviedb.org/3")
                .build();
    }

    @Override
    public Mono<List<Movie>> getAllMovies(int page) {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/discover/movie")
                        .queryParam("api_key", apiKey)
                        .queryParam("language", "en-US")
                        .queryParam("page", page) .build())
                .retrieve()
                .bodyToMono(String.class)
                .map(response -> {
                    try {
                        List<Movie> movies = objectMapper.readTree(response)
                                .get("results")
                                .traverse(objectMapper)
                                .readValueAs(new TypeReference<List<Movie>>(){});
                        // Convertir genre IDs a objetos Genre
                        for (Movie movie: movies){
                            List<Genre> genres = movie.getGenreIds().stream()
                                    .map(genreId ->{
                                        Genre genre = genreRepository.findByGenreId(genreId).orElse(null);
                                        if (genre == null){
                                            genre = new Genre();
                                            genre.setGenreId(genreId);
                                            genre = genreRepository.save(genre);
                                        }
                                        return genre;
                                    }).collect(Collectors.toList());
                            movie.setGenres(genres);
                        }
                        return movies;
                    } catch (Exception e) {
                        throw new RuntimeException("Error parsing movies", e);
                    }
                });
    }
}
