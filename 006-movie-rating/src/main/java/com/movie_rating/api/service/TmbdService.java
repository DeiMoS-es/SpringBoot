package com.movie_rating.api.service;

import com.movie_rating.api.model.dto.ApiModelDTO;
import com.movie_rating.api.model.entity.GenreApiModel;
import com.movie_rating.api.model.entity.MovieApiModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TmbdService {
    Mono<List<ApiModelDTO>> getMoviesTmbd(int page);
    Mono<Void> saveMovieTmdb(ApiModelDTO moviesDTO);
    List<GenreApiModel> mapGenres(List<Integer> genreIds);
    Mono<List<MovieApiModel>> processAndSaveMovies(int page);
    Mono<Page<ApiModelDTO>> getMoviesTmbdPage(Pageable pageable);
}
