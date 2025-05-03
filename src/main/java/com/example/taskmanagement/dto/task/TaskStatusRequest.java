package com.example.taskmanagement.dto.task;

import com.example.taskmanagement.enums.TaskStatus;

public record TaskStatusRequest(
        TaskStatus status
) {
}
