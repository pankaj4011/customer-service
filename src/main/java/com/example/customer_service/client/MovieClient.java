package com.example.customer_service.client;

import com.example.customer_service.domain.Genre;
import com.example.customer_service.dto.MovieDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Slf4j
@Service
public class MovieClient {

    private RestClient client;

    public MovieClient(RestClient client){
        this.client = client;
    }

    public List<MovieDto> getMovies(Genre genre){
        var movieList = this.client.get()
                .uri("/api/movies/{GENRE}",genre)
                .retrieve()
                .body(new ParameterizedTypeReference<List<MovieDto>>() {
                });

        log.info("Movies fetched {}",movieList);
        return movieList;

    }
}
