package com.surya.mongotest.repository;

import com.surya.mongotest.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends MongoRepository<Task , String> {
    Optional<List<Task>> findBySeverity(int severity);

    Optional<List<Task>> findByAssignee(String assignee);
}
