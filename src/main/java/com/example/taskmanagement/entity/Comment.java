package com.example.taskmanagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue
    private Integer id;
    private String text;
    private String author;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

}
