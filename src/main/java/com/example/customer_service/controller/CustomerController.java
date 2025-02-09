package com.example.customer_service.controller;

import com.example.customer_service.dto.CustomerDto;
import com.example.customer_service.dto.GenreUpdateRequestDto;
import com.example.customer_service.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable Integer id){
        var customer =this.customerService.getCustomer(id);
        return ResponseEntity.ok(customer);
    }

    @PatchMapping("/{id}/genre")
    public ResponseEntity<Void> updateGenre(@PathVariable Integer id, @RequestBody GenreUpdateRequestDto genreUpdateRequestDto){
        this.customerService.updateCustomerGenre(id,genreUpdateRequestDto);
        return ResponseEntity.noContent().build();
    }

}
