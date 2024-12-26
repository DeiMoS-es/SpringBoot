package com.movie_rating.movie.model.entity;

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
    private String backdropPath;
    @ManyToMany
    @JoinTable(
            name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres;
    private String originalLanguage;
    @NotNull
    private String originalTitle;
    @Column(columnDefinition = "LONGTEXT")
    private String description;
    private Double popularity;
    private String posterPath;
    private String releaseDate;
    private String title;
    private Double voteAverage;
    private Integer voteCount;
    private Date createdAt;
    private Date updatedAt;
    public void setPosterPath(String posterPath) {
        this.posterPath = "https://image.tmdb.org/t/p/w220_and_h330_face" + posterPath;
    }
    public void setbackdropPath(String posterPath) {
        this.posterPath = "https://image.tmdb.org/t/p/w220_and_h330_face" + posterPath;
    }
}
