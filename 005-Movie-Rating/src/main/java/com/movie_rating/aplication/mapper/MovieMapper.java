package com.movie_rating.aplication.mapper;

import com.movie_rating.aplication.dto.MovieDTO;
import com.movie_rating.domain.model.Movie;
import org.springframework.stereotype.Component;

/**
 * El Mapper se usa para convertir entre los objetos de dominio y los DTO.
 */
@Component
public class MovieMapper {
    public Movie toDomain(MovieDTO dto){
        return new Movie(dto.getMovieDtoId(),
                dto.isAdult(), dto.getBackdropPath(), dto.getOriginalTitle(),
                dto.getPosterPath(), dto.getReleaseDate(), dto.getTitle());
    }

    public MovieDTO toDTO(Movie domain){
        return new MovieDTO(domain.getMovieId(),
                domain.isAdult(), domain.getBackdropPath(), domain.getOriginalTitle(),
                domain.getPosterPath(), domain.getReleaseDate(), domain.getTitle());
    }
}
