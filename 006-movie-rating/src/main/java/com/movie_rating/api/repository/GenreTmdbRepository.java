package com.movie_rating.api.repository;

import com.movie_rating.api.model.entity.GenreApiModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenreTmdbRepository extends JpaRepository<GenreApiModel, Integer> {
    Optional<GenreApiModel> findByGenreId(int genreId);
}
