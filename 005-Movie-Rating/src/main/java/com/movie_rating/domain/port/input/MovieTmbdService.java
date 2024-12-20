package com.movie_rating.domain.port.input;

import com.movie_rating.aplication.dto.MovieTmbdDTO;
import reactor.core.publisher.Mono;

import java.util.List;

public interface MovieTmbdService {
    Mono<List<MovieTmbdDTO>> getMoviesTmbd(int page);
}
