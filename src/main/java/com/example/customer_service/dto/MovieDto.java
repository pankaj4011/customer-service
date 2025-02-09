package com.example.customer_service.dto;

import com.example.customer_service.domain.Genre;

public record MovieDto(Integer id, String title, Integer releaseYear, Genre genre) {
}
