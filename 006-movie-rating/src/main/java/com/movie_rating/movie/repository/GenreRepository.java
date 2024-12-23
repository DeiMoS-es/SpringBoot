package com.movie_rating.movie.repository;

import com.movie_rating.movie.model.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
}
