package com.example.customer_service.service;

import com.example.customer_service.client.MovieClient;
import com.example.customer_service.dto.CustomerDto;
import com.example.customer_service.dto.GenreUpdateRequestDto;
import com.example.customer_service.exceptions.CustomerNotFoundException;
import com.example.customer_service.mapper.EntityDtoMapper;
import com.example.customer_service.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private  MovieClient movieClient;
    private  CustomerRepository repository;

    public CustomerService(MovieClient movieClient, CustomerRepository repository) {
        this.movieClient = movieClient;
        this.repository = repository;
    }

    public CustomerDto getCustomer(Integer id){
       var customer = this.repository.findById(id)
                .orElseThrow(()->new CustomerNotFoundException(id));

     var movies =  this.movieClient.getMovies(customer.getFavoriteGenre());

     return EntityDtoMapper.toDto(customer,movies);
    }

    public void updateCustomerGenre(Integer id, GenreUpdateRequestDto genreUpdateRequestDto){
        var customer = this.repository.findById(id)
                .orElseThrow(()->new CustomerNotFoundException(id));
        customer.setFavoriteGenre(genreUpdateRequestDto.favoriteGenre());
        this.repository.save(customer);
    }
}
