package com.example.taskmanagement.dto.comment;

import jakarta.validation.constraints.NotBlank;

public record CommentRequest(

        @NotBlank
        String text

) {
}
