package com.movie_rating.aplication.port.output;

import com.movie_rating.domain.model.Movie;
import reactor.core.publisher.Mono;
import java.util.List;

public interface TmbdService {
    Mono<List<Movie>> getMovies(int page);
}
