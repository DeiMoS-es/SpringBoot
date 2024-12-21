package com.movie_rating.api.repository;

import com.movie_rating.api.model.entity.MovieApiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TmdbRepository extends JpaRepository<MovieApiModel, Integer> {
}
