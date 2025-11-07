package com.example.petprojectspringboot.config.exception;


import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomException extends RuntimeException {
    private String code;
    private String message;
}
