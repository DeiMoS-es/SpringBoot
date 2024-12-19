package com.movie_rating.infrastructure.adapter.repository;

import com.movie_rating.domain.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaGenreRepository extends JpaRepository<Genre, Integer> {
    Optional<Genre> findByGenreId(int genreId);
}
