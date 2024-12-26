package com.movie_rating.movie.config;

import com.movie_rating.api.model.entity.MovieApiModel;
import com.movie_rating.movie.model.entity.Movie;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovieMapper {
    private final ModelMapper modelMapper;

    public MovieMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Movie mapToMovie(MovieApiModel movieApiModel) {
        Movie movie = modelMapper.map(movieApiModel, Movie.class);
        movie.setMovieId(null); // Asegúrate de que el ID no se establezca manualmente
        return movie;
    }

    public List<Movie> mapToMovies(List<MovieApiModel> movieApiModels) {
        return movieApiModels.stream()
                .map(this::mapToMovie)
                .toList();
    }
}
