package com.surya.mongotest.service;


import com.surya.mongotest.dto.TaskDto;
import com.surya.mongotest.model.Task;
import com.surya.mongotest.exception.ResourceNotFoundException;
import com.surya.mongotest.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService{
    private final TaskRepository taskRepository;

    @Override
    public Task addTask(TaskDto t)
    {
        String id = UUID.randomUUID().toString().split("-")[0];
        Task task = new Task();
        task.setTaskId(id);
        task.setAssignee(t.getAssignee());
        task.setDescription(t.getDescription());
        task.setSeverity(t.getSeverity());
        task.setStoryPoint(t.getStoryPoint());

        return taskRepository.save(task);
    }

    @Override
    public List<Task> findAllTasks()
    {
        return taskRepository.findAll();
    }


    @Override
    public Task findTaskById(String id)
    {
        return taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Document not found"));
    }


    @Override
    public List<Task> findTaskBySeverity(int severity)
    {
        return  taskRepository.findBySeverity(severity).orElseThrow(() -> new ResourceNotFoundException("Document not found"));
    }

    @Override
    public List<Task> findTaskByAssignee(String assignee)
    {
        return  taskRepository.findByAssignee(assignee).orElseThrow(() -> new ResourceNotFoundException("Document not found"));
    }

    @Override
    public Task updateTask(Task taskRequest)
    {
        //get existing doc in mongo
        try {
            Task task =  findTaskById(taskRequest.getTaskId());
            task.setAssignee(taskRequest.getAssignee());
            task.setDescription(taskRequest.getDescription());
            task.setSeverity(taskRequest.getSeverity());
            task.setStoryPoint(taskRequest.getStoryPoint());

            return taskRepository.save(task);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }

    }

    @Override
    public  void   deleteTask(String taskId){
        taskRepository.deleteById(taskId);
    }

}
