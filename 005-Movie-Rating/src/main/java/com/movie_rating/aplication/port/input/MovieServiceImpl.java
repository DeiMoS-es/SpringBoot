package com.movie_rating.aplication.port.input;

import com.movie_rating.aplication.port.output.MovieService;
import com.movie_rating.domain.model.Movie;
import com.movie_rating.infrastructure.adapter.repository.JpaMovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MovieServiceImpl implements MovieService {

    private final JpaMovieRepository jpaMovieRepository;
    public MovieServiceImpl(JpaMovieRepository jpaMovieRepository) {
        this.jpaMovieRepository = jpaMovieRepository;
    }

    @Override
    public void saveMovie(Movie movie) {
        jpaMovieRepository.save(movie);
    }

    @Override
    public void deleteMovie(int id) {
        // Implement delete logic
    }

    @Override
    public void updateMovie(Movie movie) {
        // Implement update logic
    }

    @Override
    public List<Movie> getAllMovies(int page, int size) {
        return List.of();
    }

    @Override
    public Page<Movie> findTenMovies(Pageable pageable) {
        return jpaMovieRepository.findAll(pageable);
    }

    @Override
    public Movie findByOriginalTitle(String originalTitle) {
        return jpaMovieRepository.findByOriginalTitle(originalTitle);
    }
}