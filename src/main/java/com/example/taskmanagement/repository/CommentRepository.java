package com.example.taskmanagement.repository;

import com.example.taskmanagement.dto.comment.CommentResponse;
import com.example.taskmanagement.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query("""
                SELECT new taskmanagement.dto.comment.CommentResponse(
                    c.id, c.task.id, c.text, c.author
                )
                FROM Comment c
                WHERE
                    c.task.id = :taskId
                ORDER BY
                    c.createdAt
                    DESC
            """)
    List<CommentResponse> findAllDtosByTaskId(@Param("taskId") Integer taskId);
}
