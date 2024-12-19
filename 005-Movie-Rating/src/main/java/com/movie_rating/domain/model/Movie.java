package com.movie_rating.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.*;

import java.util.List;
import java.util.Set;

@Entity
@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private int movieId;
    @JsonProperty("adult")
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
    private List<Genre> genres;
    @JsonProperty("original_language")
    private String originalLanguage;
    @JsonProperty("original_title")
    private String originalTitle;
    @Column(columnDefinition = "LONGTEXT")
    @JsonProperty("overview")
    private String overview;
    @JsonProperty("popularity")
    private Double popularity;
    @JsonProperty("poster_path")
    private String posterPath;
    @JsonProperty("release_date")
    private String releaseDate;
    @JsonProperty("title")
    private String title;
    @JsonProperty("video")
    private boolean video;
    @JsonProperty("vote_average")
    private Double voteAverage;
    @JsonProperty("vote_count")
    private int voteCount;
    @OneToMany(mappedBy = "movie")
    private Set<Rating> ratings;

    public void setPosterPath(String posterPath) {
        this.posterPath = "https://image.tmdb.org/t/p/w220_and_h330_face" + posterPath;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }


}
