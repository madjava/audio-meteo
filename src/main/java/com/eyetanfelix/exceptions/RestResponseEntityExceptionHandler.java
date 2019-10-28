package com.eyetanfelix.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {WeatherForecastException.class})
    protected ResponseEntity<Object> handleLocationNotFoundException(WeatherForecastException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String bodyOfResponse = "Could not find data for location";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {JsonProcessingException.class})
    protected ResponseEntity<Object> handleJsonProcessingException(JsonProcessingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String bodyOfResponse = "Error Processing MapBox response";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }


}