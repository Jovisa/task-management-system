package com.example.taskmanagement.dto.task;

import jakarta.validation.constraints.Pattern;

public record TaskAssignRequest(

        @Pattern(
                regexp = "none|^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                flags = Pattern.Flag.CASE_INSENSITIVE,
                message = "Must be a valid email or 'none'"
        )
        String assignee
) {
}
