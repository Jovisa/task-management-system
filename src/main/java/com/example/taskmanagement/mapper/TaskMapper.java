package com.example.taskmanagement.mapper;


import com.example.taskmanagement.dto.task.TaskRequest;
import com.example.taskmanagement.dto.task.TaskResponse;
import com.example.taskmanagement.entity.Task;
import com.example.taskmanagement.enums.TaskStatus;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;

import static com.example.taskmanagement.util.Constants.NO_ASSIGNEE;

@Component
public class TaskMapper {


    public Task toEntity(TaskRequest taskRequest, String username) {
        Task task = new Task();
        task.setTitle(taskRequest.title());
        task.setDescription(taskRequest.description());
        task.setAuthor(username);
        task.setAssignee(NO_ASSIGNEE);
        task.setStatus(TaskStatus.CREATED);
        task.setCreatedAt(LocalDateTime.now());
        return task;
    }

    public TaskResponse toResponse(Task task) {
        return new TaskResponse(
                String.valueOf(task.getId()),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getAuthor().toLowerCase(),
                task.getAssignee().toLowerCase()
        );
    }

}