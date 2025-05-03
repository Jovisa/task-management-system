package com.example.taskmanagement.exception;

import com.example.taskmanagement.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        String message = ex.getFieldErrors().stream()
                .map(error -> error.getField() + ":" + error.getDefaultMessage())
                .reduce((m1, m2) -> m1 + ";" + m2)
                .orElse("Validation Error");

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.toString(),
                message,
                LocalDateTime.now().toString()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

}
