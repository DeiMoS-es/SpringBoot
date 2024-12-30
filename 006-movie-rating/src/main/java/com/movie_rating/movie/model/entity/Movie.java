package com.movie_rating.movie.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@UuidGenerator
    private Long movieId;
    @NotNull
    private boolean adult;
    @JsonProperty("backdrop_path")
    private String backdropPath;
    @ManyToMany
    @JoinTable(
            name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres;
    @JsonProperty("original_language")
    private String originalLanguage;
    @NotNull
    @JsonProperty("original_title")
    private String originalTitle;
    @Column(columnDefinition = "LONGTEXT")
    @JsonProperty("overview")
    private String description;
    private Double popularity;
    @JsonProperty("poster_path")
    private String posterPath;
    private String releaseDate;
    private String title;
    @JsonProperty("vote_average")
    private Double voteAverage;
    @JsonProperty("vote_count")
    private Integer voteCount;
    private Date createdAt;
    private Date updatedAt;
    public void setPosterPath(String posterPath) {
        this.posterPath = "https://image.tmdb.org/t/p/w220_and_h330_face" + posterPath;
    }
    public void setBackdropPath(String posterPath) {
        this.backdropPath = "https://image.tmdb.org/t/p/w220_and_h330_face" + posterPath;
    }
}
