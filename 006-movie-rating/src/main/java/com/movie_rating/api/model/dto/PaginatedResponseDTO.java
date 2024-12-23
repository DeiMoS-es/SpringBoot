package com.movie_rating.api.model.dto;

import java.util.List;

/**
 * DTO para encapsular una respuesta paginada.
 *
 * @param <T> el tipo de los elementos en la lista de resultados
 */
public class PaginatedResponseDTO<T> {
    private List<T> content;
    private int page;
    private int totalPages;
    private long totalResults;
    private boolean isLast;
    private boolean isFirst;

    public PaginatedResponseDTO(List<T> content, int page, int totalPages, long totalResults, boolean isLast, boolean isFirst) {
        this.content = content;
        this.page = page;
        this.totalPages = totalPages;
        this.totalResults = totalResults;
        this.isLast = isLast;
        this.isFirst = isFirst;
    }

    // Getters y setters
    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(long totalResults) {
        this.totalResults = totalResults;
    }

    public boolean isLast() {
        return isLast;
    }

    public void setLast(boolean last) {
        isLast = last;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }
}