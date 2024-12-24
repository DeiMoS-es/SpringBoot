package com.movie_rating.movie.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Clase encargada de devolver la respuesta paginada de las películas
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponsePaginated<T> {
    private List<T> content;
    private int page;
    private int totalPages;
    private long totalResults;
    private boolean isLast;
    private boolean isFirst;
}
