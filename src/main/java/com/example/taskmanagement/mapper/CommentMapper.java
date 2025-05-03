package com.example.taskmanagement.mapper;

import com.example.taskmanagement.dto.comment.CommentResponse;
import com.example.taskmanagement.entity.Comment;
import org.springframework.stereotype.Component;


@Component
public class CommentMapper {

    public CommentResponse toResponse(Comment comment) {
        return CommentResponse.builder()
                .id(String.valueOf(comment.getId()))
                .taskId(String.valueOf(comment.getTask().getId()))
                .text(comment.getText())
                .author(comment.getAuthor())
                .build();
    }

}
