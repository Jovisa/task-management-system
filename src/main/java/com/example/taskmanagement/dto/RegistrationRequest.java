package com.example.taskmanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegistrationRequest(

        @NotBlank(message = "Email must not be blanc")
        @Email(message = "Email is invalid")
        String email,

        @NotBlank(message = "Password must not be blanc")
        @Size(min = 6, message = "Password must be at least 6 characters long")
        String password
) {
}

