package com.example.taskmanagement.dto.task;

import jakarta.validation.constraints.NotBlank;

public record TaskRequest(

        @NotBlank(message = "Title must not be blanc")
        String title,

        @NotBlank(message = "Description must not be blanc")
        String description

) {
}