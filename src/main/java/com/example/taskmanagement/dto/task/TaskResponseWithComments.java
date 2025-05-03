package com.example.taskmanagement.dto.task;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.example.taskmanagement.enums.TaskStatus;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record TaskResponseWithComments(
        String id,
        String title,
        String description,
        TaskStatus status,
        String author,
        String assignee,
        Long totalComments
) {
    public static TaskResponseWithComments from(
            Integer id,
            String title,
            String description,
            TaskStatus status,
            String author,
            String assignee,
            Long totalComments
    ) {
        return new TaskResponseWithComments(
                id != null ? String.valueOf(id) : null,
                title,
                description,
                status,
                author,
                assignee,
                totalComments
        );
    }
}
