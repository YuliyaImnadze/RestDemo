package com.example.restdemo.exeption;

import org.springframework.http.HttpStatus;

import java.util.List;

public class BasicPersonErrorResponse {

    private List<String> message;
    private HttpStatus status;

    public BasicPersonErrorResponse(List<String> message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }


    public List<String> getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
