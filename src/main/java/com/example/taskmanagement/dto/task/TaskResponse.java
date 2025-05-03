package com.example.taskmanagement.dto.task;


import com.example.taskmanagement.enums.TaskStatus;

public record TaskResponse(
        String id,
        String title,
        String description,
        TaskStatus status,
        String author,
        String assignee
) {
}
