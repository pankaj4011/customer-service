package com.example.customer_service.dto;

import com.example.customer_service.domain.Genre;

import java.util.List;

public record CustomerDto(Integer id, String name, Genre favoriteGenre, List<MovieDto> recommendedMovies) {
}
