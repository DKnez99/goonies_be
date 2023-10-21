package com.goonsquad.goonies.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = EntityNotFoundException.class)
    public ExceptionDto handleEntityNotFoundException(RuntimeException ex, WebRequest request) {
        return new ExceptionDto(ex, request, HttpStatus.NOT_FOUND.value());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            BadRequestException.class,
            JwtException.class,
            DataIntegrityViolationException.class
    })
    public ExceptionDto handleBadRequestException(RuntimeException ex, WebRequest request) {
        return new ExceptionDto(ex, request, HttpStatus.BAD_REQUEST.value());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BindException.class)
    public List<ExceptionDto> handleValidationException(BindException ex, WebRequest request) {
        return ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new ExceptionDto(error, request, HttpStatus.BAD_REQUEST.value()))
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = ForbiddenException.class)
    public ExceptionDto handleForbiddenException(RuntimeException ex, WebRequest request) {
        return new ExceptionDto(ex, request, HttpStatus.FORBIDDEN.value());
    }

}
