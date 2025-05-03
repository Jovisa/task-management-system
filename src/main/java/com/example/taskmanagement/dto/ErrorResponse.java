package com.example.taskmanagement.dto;

public record ErrorResponse(
        String status,
        String message,
        String timestamp
) {
}