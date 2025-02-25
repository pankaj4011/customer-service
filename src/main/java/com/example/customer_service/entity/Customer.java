package com.example.customer_service.entity;

import com.example.customer_service.domain.Genre;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer {
    @Id
    private Integer id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Genre favoriteGenre;
}
