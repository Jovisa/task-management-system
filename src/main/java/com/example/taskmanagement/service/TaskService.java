package com.example.taskmanagement.service;

import com.example.taskmanagement.dto.comment.CommentRequest;
import com.example.taskmanagement.dto.comment.CommentResponse;
import com.example.taskmanagement.dto.task.TaskRequest;
import com.example.taskmanagement.dto.task.TaskResponse;
import com.example.taskmanagement.dto.task.TaskResponseWithComments;
import com.example.taskmanagement.entity.Comment;
import com.example.taskmanagement.entity.Task;
import com.example.taskmanagement.enums.TaskStatus;
import com.example.taskmanagement.exception.TaskNotFoundException;
import com.example.taskmanagement.exception.UserNotFoundException;
import com.example.taskmanagement.mapper.CommentMapper;
import com.example.taskmanagement.mapper.TaskMapper;
import com.example.taskmanagement.repository.AppUserRepository;
import com.example.taskmanagement.repository.CommentRepository;
import com.example.taskmanagement.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;

import static com.example.taskmanagement.util.Constants.NO_ASSIGNEE;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskMapper taskMapper;
    private final TaskRepository taskRepository;
    private final AppUserRepository userRepository;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;


    public TaskResponse createTask(TaskRequest request, String username) {
        Task savedTask = taskRepository.save(taskMapper.toEntity(request, username));
        return taskMapper.toResponse(savedTask);
    }


    //using JPL Query in repository
    public List<TaskResponseWithComments> getAllTasks(String author, String assignee) {
        String a = Strings.isBlank(author) ? null : author;
        String b = Strings.isBlank(assignee) ? null : assignee;
        return taskRepository.findAllDtoByOptionalAuthorAndAssignee(a, b);
    }

//    // using Specification (TaskSpecification)
//    public List<TaskResponse> getAllTasks(String author, String assignee) {
//        Specification<Task> spec = Specification
//                .where(TaskSpecifications.hasAuthor(author))
//                .and(TaskSpecifications.hasAssignee(assignee));
//
//        Sort sort = Sort.by("createdAt").descending();
//        List<Task> tasks = taskRepository.findAll(spec, sort);
//
//        return taskMapper.toResponse(tasks);
//    }


    // === Task Assignment ===

    public TaskResponse assignTask(Integer taskId, String assigneeEmail, String userEmail) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found, taskId is invalid"));

        if (!task.getAuthor().equals(userEmail)) {
            throw new AccessDeniedException("You are not the author of this task");
        }
        if (assigneeUserNotExists(assigneeEmail)) {
            throw new UserNotFoundException("Trying to assign task to non existing user");
        }

        task.setAssignee(assigneeEmail);
        return taskMapper.toResponse(taskRepository.save(task));
    }

    private boolean assigneeUserNotExists(String assigneeEmail) {
        return !assigneeUserExists(assigneeEmail);
    }

    private boolean assigneeUserExists(String assigneeEmail) {
        return NO_ASSIGNEE.equals(assigneeEmail)
                || userRepository.existsByEmailIgnoreCase(assigneeEmail);
    }


    // === Task Status Update ===

    public TaskResponse setStatus(Integer taskId, TaskStatus status, String userEmail) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found, taskId is invalid"));

        if (isNotAuthorOrAssignee(userEmail, task)) {
            throw new AccessDeniedException("You are not the Author or Assignee of this Task");
        }

        task.setStatus(status);
        return taskMapper.toResponse(taskRepository.save(task));
    }

    private boolean isNotAuthorOrAssignee(String userEmail, Task task) {
        return !isAuthorOrAssignee(userEmail, task);
    }

    private boolean isAuthorOrAssignee(String userEmail, Task task) {
        return userEmail.equals(task.getAuthor())
                || userEmail.equals(task.getAssignee());
    }


    // === Comments ===

    @Transactional
    public CommentResponse postComment(Integer taskId, CommentRequest commentRequest, String userEmail) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found, taskId is invalid"));

        Comment comment = Comment.builder()
                .task(task)
                .text(commentRequest.text())
                .author(userEmail)
                .createdAt(LocalDateTime.now())
                .build();

        Comment savedComment = commentRepository.save(comment);
        task.getComments().add(savedComment);
        taskRepository.save(task);

        return commentMapper.toResponse(savedComment);
    }

    public List<CommentResponse> getComments(Integer taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found, taskId is invalid"));
        return commentRepository.findAllDtosByTaskId(taskId);
    }

}
