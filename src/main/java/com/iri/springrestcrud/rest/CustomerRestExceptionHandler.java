package com.iri.springrestcrud.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerRestExceptionHandler {

    // add an exception handler for CustomerNotFoundException

    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> handlerException(CustomerNotFoudException exception) {

        // crate CustomerErrorResponse
        CustomerErrorResponse errorResponse = new CustomerErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                System.currentTimeMillis()
        );

        //return ResponseEntity

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // add another exception handler to catch any exception


    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> handleAllException(Exception e) {
        CustomerErrorResponse error = new CustomerErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


}
