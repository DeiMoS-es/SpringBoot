package com.movie_rating.infrastructure.adapter.repository;

import com.movie_rating.domain.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaMovieRepository extends JpaRepository<Movie, Integer> {
    Movie findByOriginalTitle(String originalTitle);
}
