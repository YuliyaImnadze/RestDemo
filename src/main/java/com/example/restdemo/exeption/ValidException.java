package com.example.restdemo.exeption;

import lombok.Data;

@Data
public class ValidException extends RuntimeException {
    public ValidException(String message) {
        super(message);
    }
}
