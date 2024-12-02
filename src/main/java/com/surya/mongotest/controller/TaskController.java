package com.surya.mongotest.controller;

import com.surya.mongotest.dto.TaskDto;
import com.surya.mongotest.exception.ResourceNotFoundException;
import com.surya.mongotest.model.Task;
import com.surya.mongotest.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;


    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody TaskDto task)
    {
        try {
            return taskService.addTask(task);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @GetMapping
    private List<Task> getAllTasks()
    {
        return taskService.findAllTasks();
    }

    @GetMapping("/{taskId}")
    public Task getTask(@PathVariable String taskId)
    {
        try {
            return taskService.findTaskById(taskId);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }


    @GetMapping("/severity/{severity}")
    public List<Task> findTaskBySeverity(@PathVariable int severity)
    {

        try {
            return  taskService.findTaskBySeverity(severity);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @GetMapping("/assignee/{assignee}")
    public List<Task> findTaskByAssignee(@PathVariable String assignee)
    {

        try {
            return  taskService.findTaskByAssignee(assignee);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @PutMapping
    public Task modifyTask(@RequestBody Task task){
        try {
            return  taskService.updateTask(task);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{taskId}")
    public String deleteTask(@PathVariable String taskId){
        taskService.deleteTask(taskId);
        return "deleted";
    }

}
