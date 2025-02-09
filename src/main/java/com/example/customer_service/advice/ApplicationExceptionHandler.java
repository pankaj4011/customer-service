package com.example.customer_service.advice;

import com.example.customer_service.exceptions.CustomerNotFoundException;
import org.hibernate.annotations.NotFound;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ProblemDetail handleException(CustomerNotFoundException exception){
        var problemdetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(404),exception.getMessage());
        problemdetail.setTitle("Customer not found");
        return  problemdetail;
    }

}
