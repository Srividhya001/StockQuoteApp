package com.stockQuoteApplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StockExceptionHandler {

    @ExceptionHandler(value = {StockNotFoundException.class})
    public ResponseEntity<Object> handlestockNotFoundException
            (StockNotFoundException stockNotFoundException) {
        StockException stockException = new StockException(
                stockNotFoundException.getMessage(),
                stockNotFoundException.getCause(),
                HttpStatus.NOT_FOUND
        );

        return new ResponseEntity<>(stockException, HttpStatus.NOT_FOUND);
    }
}
