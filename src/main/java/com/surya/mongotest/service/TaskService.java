package com.surya.mongotest.service;

import com.surya.mongotest.dto.TaskDto;
import com.surya.mongotest.model.Task;

import java.util.List;

public interface TaskService {

    Task addTask(TaskDto t);

    List<Task> findAllTasks();

    Task findTaskById(String id);

    List<Task> findTaskBySeverity(int severity);

    List<Task> findTaskByAssignee(String assignee);

    Task updateTask(Task taskRequest);

    void   deleteTask(String taskId);
}
