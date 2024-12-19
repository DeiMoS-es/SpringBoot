package com.movie_rating.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    private int movieId;
    private boolean adult;
    private String backdropPath;
    private List<Integer> genreIds;
    private String originalLanguage;
    private String originalTitle;
    private String overview;
    private Double popularity;
    private String posterPath;
    private String releaseDate;
    private String title;
    private boolean video;
    private Double voteAverage;
    private int voteCount;

    public Movie(int movieDtoId, boolean adult, String backdropPath, String originalTitle, String posterPath, String releaseDate, String title) {
    }
}
