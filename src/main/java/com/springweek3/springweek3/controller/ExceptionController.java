package com.springweek3.springweek3.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> BadRequestException(final IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex);    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> NullPointerException(final NullPointerException e) {
        return ResponseEntity.badRequest().build();
    }
}
