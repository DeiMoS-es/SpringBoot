package com.movie_rating.movie.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * DTO para encapsular los datos de una película en la respuesta.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {
    private boolean adult;
    private String backdropPath;
    private List<Integer> genreIds;
    private String originalLanguage;
    private String originalTitle;
    private String description;
    private Double popularity;
    private String posterPath;
    private String releaseDate;
    private String title;
    private Double voteAverage;
    private Integer voteCount;
    private Date createdAt;
    private Date updatedAt;
}
