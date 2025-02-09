package com.example.customer_service.mapper;

import com.example.customer_service.dto.CustomerDto;
import com.example.customer_service.dto.MovieDto;
import com.example.customer_service.entity.Customer;

import java.util.List;

public class EntityDtoMapper {
    public static CustomerDto toDto(Customer customer, List<MovieDto> movies){
        return new CustomerDto(customer.getId(),customer.getName().toUpperCase(),customer.getFavoriteGenre(),movies);
    }
}
