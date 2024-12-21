package com.movie_rating.api.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.movie_rating.api.model.entity.GenreApiModel;
import com.movie_rating.api.model.entity.MovieApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiModelDTO {
    @JsonProperty("id")
    private int id;
    private boolean adult;
    @JsonProperty("backdrop_path")
    private String backdropPath;
    @JsonProperty("genre_ids")
    private List<Integer> genreIds;
    @JsonProperty("original_language")
    private String originalLanguage;
    @JsonProperty("original_title")
    private String originalTitle;
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

    // Method to convert DTO to entity
    public MovieApiModel toEntity(List<GenreApiModel> genreEntities) {
        MovieApiModel entity = new MovieApiModel();
        entity.setMovieId(this.id);
        entity.setAdult(this.adult);
        entity.setBackdropPath(this.backdropPath);
        entity.setGenreIds(this.genreIds);
        entity.setGenres(genreEntities);
        entity.setOriginalLanguage(this.originalLanguage);
        entity.setOriginalTitle(this.originalTitle);
        entity.setOverview(this.overview);
        entity.setPopularity(this.popularity);
        entity.setPosterPath(this.posterPath);
        entity.setReleaseDate(this.releaseDate);
        entity.setTitle(this.title);
        entity.setVideo(this.video);
        entity.setVoteAverage(this.voteAverage);
        entity.setVoteCount(this.voteCount);
        return entity;
    }

    // Method to convert entity to DTO
    public ApiModelDTO toDTO(MovieApiModel entity) {
        ApiModelDTO dto = new ApiModelDTO();
        dto.setId(entity.getMovieId());
        dto.setAdult(entity.isAdult());
        dto.setBackdropPath(entity.getBackdropPath());
        dto.setGenreIds(entity.getGenreIds());
        dto.setOriginalLanguage(entity.getOriginalLanguage());
        dto.setOriginalTitle(entity.getOriginalTitle());
        dto.setOverview(entity.getOverview());
        dto.setPopularity(entity.getPopularity());
        dto.setPosterPath(entity.getPosterPath());
        dto.setReleaseDate(entity.getReleaseDate());
        dto.setTitle(entity.getTitle());
        dto.setVideo(entity.isVideo());
        dto.setVoteAverage(entity.getVoteAverage());
        dto.setVoteCount(entity.getVoteCount());
        return dto;
    }
}

