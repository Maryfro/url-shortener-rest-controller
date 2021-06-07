package de.maryfro.urlshortenerrestcontroller.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String DEFAULT_ERROR_VIEW = "your short url is not valid";

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {EntityNotFoundException.class})
    protected ResponseEntity<Object> handleNotFoundException(
            RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, DEFAULT_ERROR_VIEW,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}


