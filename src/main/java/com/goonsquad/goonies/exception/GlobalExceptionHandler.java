package com.goonsquad.goonies.exception;

import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = EntityNotFoundException.class)
    public ExceptionDto handleEntityNotFoundException(RuntimeException ex, WebRequest request) {
        return new ExceptionDto(ex, request, HttpStatus.NOT_FOUND.value());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BadRequestException.class)
    public ExceptionDto handleBadRequestException(RuntimeException ex, WebRequest request) {
        return new ExceptionDto(ex, request, HttpStatus.BAD_REQUEST.value());
    }

}
