package com.movie_rating.infrastructure.client;

import com.movie_rating.aplication.dto.MovieTmbdDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class TmbdClient {
    private final WebClient webClient;

    @Value("${TMBD.API.KEY}")
    private String apiKey;

    public TmbdClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.themoviedb.org/3").build();
    }

    public Mono<List<MovieTmbdDTO>> getMoviesTmbd(int page) {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/discover/movie")
                        .queryParam("api_key", apiKey)
                        .queryParam("language", "es-ES")
                        .queryParam("page", page)
                        .build())
                .retrieve()
                .bodyToMono(TmbdResponse.class) // el método bodyToMono de WebClient se utiliza para convertir el cuerpo de la respuesta HTTP en un objeto de tipo TmbdResponse. Jackson, la biblioteca de deserialización de JSON, se encarga de convertir el JSON recibido en una instancia de TmbdResponse.
                .map(TmbdResponse::getResults); // se utiliza para extraer la lista de MovieTmbdDTO de la propiedad results del objeto TmbdResponse
    }

    private static class TmbdResponse {
        @JsonProperty("results")
        private List<MovieTmbdDTO> results;

        public List<MovieTmbdDTO> getResults() {
            return results;
        }

        public void setResults(List<MovieTmbdDTO> results) {
            this.results = results;
        }
    }
}