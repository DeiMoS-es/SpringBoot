package com.movie_rating.aplication.service;

import com.movie_rating.aplication.dto.MovieTmbdDTO;
import com.movie_rating.domain.port.input.MovieTmbdService;
import com.movie_rating.infrastructure.client.TmbdClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class MovieTmbdServiceImpl implements MovieTmbdService {
    private final TmbdClient tmbdClient;
    @Autowired
    public MovieTmbdServiceImpl(TmbdClient tmbdClient){
        this.tmbdClient = tmbdClient;
    }

    @Override
    public Mono<List<MovieTmbdDTO>> getMoviesTmbd(int page) {
        return tmbdClient.getMoviesTmbd(page);
    }
}
