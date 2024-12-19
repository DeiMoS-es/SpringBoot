package com.movie_rating.aplication.port.output;

import com.movie_rating.domain.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovieService {
    void saveMovie(Movie movie);
    void deleteMovie(int id);
    void updateMovie(Movie movie);
    List<Movie> getAllMovies(int page, int size);
    Page<Movie> findTenMovies(Pageable pageable);
    Movie findByOriginalTitle(String originalTitle);
}
