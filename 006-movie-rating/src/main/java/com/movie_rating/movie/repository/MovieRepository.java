package com.movie_rating.movie.repository;

import com.movie_rating.movie.model.dto.MovieDTO;
import com.movie_rating.movie.model.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findByTitleAndReleaseDate(String title, String releaseDate);
    Optional<Movie> findByTitle(String title);
}
