package com.example.restdemo.exeption;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<String> listErrors = new ArrayList<>();
        List<FieldError> fieldError = ex.getBindingResult().getFieldErrors();
        for (FieldError error : fieldError) {
            String defaultMessage = error.getDefaultMessage();
            listErrors.add(defaultMessage);
        }

        BasicPersonErrorResponse response = new BasicPersonErrorResponse(listErrors, (HttpStatus) status);
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler
    protected ResponseEntity<Object> responseException(EntityNotFoundException exception, WebRequest request) {
        BasicPersonErrorResponse basicPersonErrorResponse = new BasicPersonErrorResponse(List.of("Person not found"), HttpStatus.NOT_FOUND);
        return handleExceptionInternal(exception, basicPersonErrorResponse, new HttpHeaders(), basicPersonErrorResponse.getStatus(), request);
    }

    @ExceptionHandler
    protected ResponseEntity<Object> validException(ValidException exception, WebRequest request) {
        BasicPersonErrorResponse basicPersonErrorResponse = new BasicPersonErrorResponse(List.of(exception.getMessage()), HttpStatus.IM_USED);
        return handleExceptionInternal(exception, basicPersonErrorResponse, new HttpHeaders(), basicPersonErrorResponse.getStatus(), request);
    }


}
