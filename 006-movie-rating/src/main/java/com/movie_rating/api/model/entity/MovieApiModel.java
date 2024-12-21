package com.movie_rating.api.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieApiModel {
    @Id
    @JsonProperty("id")
    private int movieId;
    private boolean adult;
    @JsonProperty("backdrop_path")
    private String backdropPath;
    @Transient
    @JsonProperty("genre_ids")
    private List<Integer> genreIds;
    @ManyToMany
    @JoinTable(
            name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<GenreApiModel> genres;
    @JsonProperty("original_language")
    private String originalLanguage;
    @JsonProperty("original_title")
    private String originalTitle;
    @Column(columnDefinition = "LONGTEXT")
    private String overview;
    private Double popularity;
    @JsonProperty("poster_path")
    private String posterPath;
    @JsonProperty("release_date")
    private String releaseDate;
    private String title;
    private boolean video;
    @JsonProperty("vote_average")
    private Double voteAverage;
    @JsonProperty("vote_count")
    private int voteCount;

    public void setPosterPath(String posterPath) {
        this.posterPath = "https://image.tmdb.org/t/p/w220_and_h330_face" + posterPath;
    }
}
