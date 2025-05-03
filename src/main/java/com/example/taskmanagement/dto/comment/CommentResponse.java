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
    public CommentResponse(Integer id, Integer taskId, String text, String author) {
        this(String.valueOf(id), String.valueOf(taskId), text, author);
    }
}
