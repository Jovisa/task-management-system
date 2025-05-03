package com.example.taskmanagement.dto.comment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record CommentResponse(
        String id,
        @JsonProperty("task_id")
        String taskId,
        String text,
        String author
) {
    public static CommentResponse from(Integer id, Integer taskId, String text, String author) {
        return new CommentResponse(
                id != null ? String.valueOf(id) : null,
                taskId != null ? String.valueOf(taskId) : null,
                text,
                author
        );
    }
}
