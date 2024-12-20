package com.movie_rating.aplication.mapper;

import com.movie_rating.aplication.dto.MovieTmbdDTO;
import com.movie_rating.infrastructure.entity.MovieTmbdEntity;
import org.springframework.stereotype.Component;

@Component
public class MovieTmbdMapper {

    public MovieTmbdDTO toDTO(MovieTmbdEntity entity){
        return MovieTmbdDTO.builder()
                .movieId(entity.getMovieId())
                .adult(entity.isAdult())
                .backdropPath(entity.getBackdropPath())
                .genreIds(entity.getGenreIds())
                .originalLanguage(entity.getOriginalLanguage())
                .originalTitle(entity.getOriginalTitle())
                .overview(entity.getOverview())
                .popularity(entity.getPopularity())
                .posterPath(entity.getPosterPath())
                .releaseDate(entity.getReleaseDate())
                .title(entity.getTitle())
                .video(entity.isVideo())
                .voteAverage(entity.getVoteAverage())
                .voteCount(entity.getVoteCount())
                .build();
    }

    public MovieTmbdEntity toEntity(MovieTmbdDTO dto){
        return MovieTmbdEntity.builder()
                .movieId(dto.getMovieId())
                .adult(dto.isAdult())
                .backdropPath(dto.getBackdropPath())
                .genreIds(dto.getGenreIds())
                .originalLanguage(dto.getOriginalLanguage())
                .originalTitle(dto.getOriginalTitle())
                .overview(dto.getOverview())
                .popularity(dto.getPopularity())
                .posterPath(dto.getPosterPath())
                .releaseDate(dto.getReleaseDate())
                .title(dto.getTitle())
                .video(dto.isVideo())
                .voteAverage(dto.getVoteAverage())
                .voteCount(dto.getVoteCount())
                .build();
    }
}
