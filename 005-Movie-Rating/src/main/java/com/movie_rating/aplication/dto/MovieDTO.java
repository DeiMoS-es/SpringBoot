package com.movie_rating.aplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  El DTO se usa para recibir y enviar datos en las interacciones con el cliente.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {
    private int movieDtoId;
    private boolean adult;
    private String backdropPath;
    //private List<Genre> genreIds;
    private String originalTitle;
    private String posterPath;
    private String releaseDate;
    private String title;

}
