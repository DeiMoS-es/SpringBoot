package com.movie_rating.aplication.port.input;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie_rating.aplication.port.output.TmbdService;
import com.movie_rating.domain.model.Genre;
import com.movie_rating.domain.model.Movie;
import com.movie_rating.infrastructure.adapter.repository.JpaGenreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TmbdServiceImpl implements TmbdService {
    private final WebClient webClient;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final JpaGenreRepository genreRepository;

    @Value("${TMDB_API_KEY}")
    private String apiKey;

    public TmbdServiceImpl(WebClient.Builder webClientBuilder, JpaGenreRepository genreRepository) {
        this.webClient = webClientBuilder.baseUrl("https://api.themoviedb.org/3").build();
        this.genreRepository = genreRepository;
    }

    @Override
    public Mono<List<Movie>> getMovies(int page) {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/discover/movie")
                        .queryParam("api_key", apiKey)
                        .queryParam("language", "es-ES")
                        .queryParam("page", page)
                        .build())
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
