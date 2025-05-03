package com.example.taskmanagement.util;

import com.example.taskmanagement.entity.Task;
import org.springframework.data.jpa.domain.Specification;


import static org.apache.logging.log4j.util.Strings.isBlank;

public class TaskSpecifications {

    public static Specification<Task> hasAuthor(String author) {
        return (root, query, cb)
                -> isBlank(author) ? null : cb.equal(root.get("author"), author);
    }

    public static Specification<Task> hasAssignee(String assignee) {
        return (root, query, cb)
                -> isBlank(assignee) ? null : cb.equal(root.get("assignee"), assignee);
    }
}
