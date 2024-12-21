package com.movie_rating.api.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.movie_rating.api.model.dto.ApiModelDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Clase que representa un cliente para la API de TMDb
 * Se utiliza para realizar solicitudes a la API de TMDb y recuperar películas
 * de la base de datos de la API de TMDb
 */
@Component
public class TmdbClient {
    private final WebClient webClient;

    public TmdbClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.themoviedb.org/3").build();
    }

    @Value("${TMDB_API_KEY}")
    private String apiKey;

    public Mono<List<ApiModelDTO>> getMovies(int page) {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/discover/movie")
                        .queryParam("api_key", apiKey)
                        .queryParam("language", "es-ES")
                        .queryParam("page", page)
                        .build())
                .retrieve()
                .bodyToMono(TmdbResponse.class)
                .map(TmdbResponse::getResults);
    }
    // Clase interna que representa la respuesta de la API de TMDb
    // Se utiliza para deserializar la respuesta JSON de la API de TMDb
    // en una instancia de TmdbResponse que contiene una lista de ApiModelDTO (películas) en la propiedad results
    private static class TmdbResponse {
        @JsonProperty("results")
        private List<ApiModelDTO> results;
        public List<ApiModelDTO> getResults() {
            return results;
        }
        public void setResults(List<ApiModelDTO> results) {
            this.results = results;
        }
    }
}
