package com.example.taskmanagement.controller;

import com.example.taskmanagement.dto.comment.CommentRequest;
import com.example.taskmanagement.dto.comment.CommentResponse;
import com.example.taskmanagement.dto.task.*;
import com.example.taskmanagement.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;


    @GetMapping
    public ResponseEntity<List<TaskResponseWithComments>> getAllTasks(
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String assignee) {

        List<TaskResponseWithComments> tasks = taskService.getAllTasks(author, assignee);
        return ResponseEntity.ok(tasks);
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskRequest taskRequest,
                                                   @AuthenticationPrincipal Jwt jwt) {

        TaskResponse response = taskService.createTask(taskRequest, jwt.getSubject());
        return ResponseEntity.ok(response);
    }


    @PutMapping("/{taskId}/assign")
    public ResponseEntity<TaskResponse> addAssignee(@PathVariable Integer taskId,
                                                    @Valid @RequestBody TaskAssignRequest assignRequest,
                                                    @AuthenticationPrincipal Jwt jwt) {

        TaskResponse response = taskService.assignTask(taskId, assignRequest.assignee(), jwt.getSubject());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{taskId}/status")
    public ResponseEntity<TaskResponse> setStatus(@PathVariable Integer taskId,
                                                  @Valid @RequestBody TaskStatusRequest statusRequest,
                                                  @AuthenticationPrincipal Jwt jwt) {

        TaskResponse response = taskService.setStatus(taskId, statusRequest.status(), jwt.getSubject());
        return ResponseEntity.ok(response);
    }


    @PostMapping("/{taskId}/comments")
    public ResponseEntity<CommentResponse> postComment(@PathVariable Integer taskId,
                                                       @Valid @RequestBody CommentRequest commentRequest,
                                                       @AuthenticationPrincipal Jwt jwt) {

        CommentResponse response = taskService.postComment(taskId, commentRequest, jwt.getSubject());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{taskId}/comments")
    public ResponseEntity<List<CommentResponse>> getComments(@PathVariable Integer taskId) {

        List<CommentResponse> response = taskService.getComments(taskId);
        return ResponseEntity.ok(response);
    }

}
