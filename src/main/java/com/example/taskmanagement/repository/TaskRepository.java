package com.example.taskmanagement.repository;

import com.example.taskmanagement.dto.task.TaskResponseWithComments;
import com.example.taskmanagement.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface TaskRepository extends
        JpaRepository<Task, Integer>,
        JpaSpecificationExecutor<Task> {


    @Query("""
                SELECT t FROM Task t
                WHERE (:author IS NULL OR t.author = :author)
                  AND (:assignee IS NULL OR t.assignee = :assignee)
                ORDER BY t.createdAt DESC
            """)
    List<Task> findAllByOptionalAuthorAndAssignee(@Param("author") String author, @Param("assignee") String assignee);

    @Query("""
                SELECT new taskmanagement.dto.task.TaskResponseWithComments(
                    t.id,
                    t.title,
                    t.description,
                    t.status,
                    t.author,
                    t.assignee,
                    COUNT(c)
                )
                FROM Task t
                LEFT JOIN t.comments c
                WHERE (:author IS NULL OR t.author = :author)
                  AND (:assignee IS NULL OR t.assignee = :assignee)
                GROUP BY t.id, t.title, t.description, t.status, t.author, t.assignee
                ORDER BY t.createdAt DESC
            """)
    List<TaskResponseWithComments> findAllDtoByOptionalAuthorAndAssignee(
            @Param("author") String author,
            @Param("assignee") String assignee
    );

}
