package com.example.taskmanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.example.taskmanagement.enums.TaskStatus;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private String description;
    private TaskStatus status;
    private String author;
    private String assignee;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

}


