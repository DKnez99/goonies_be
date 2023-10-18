package com.goonsquad.goonies.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;

@Data
@AllArgsConstructor
public class ExceptionDto {
    private int status;
    private String message;
    private String error;
    private String path;
    private Instant timestamp = Instant.now();


    public ExceptionDto(Exception exception, WebRequest webRequest, int errorStatus) {
        this.status = errorStatus;
        this.message = exception.getMessage();
        this.error = getSimpleName(exception);
        this.path = getRequestURI(webRequest);
    }

    public ExceptionDto(FieldError exception, WebRequest webRequest, int errorStatus) {
        this.status = errorStatus;
        this.message = exception.getDefaultMessage();
        this.error = getSimpleName(exception);
        this.path = getRequestURI(webRequest);
    }

    private String getRequestURI(WebRequest request) {
        return ((ServletWebRequest) request).getRequest().getRequestURI();
    }

    private String getSimpleName(Object obj) { return obj.getClass().getSimpleName(); }
}
