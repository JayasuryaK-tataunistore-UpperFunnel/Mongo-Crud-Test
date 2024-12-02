package com.surya.mongotest.dto;


import lombok.Data;

@Data
public class TaskDto {
    private String description;
    private int severity;
    private String assignee;
    private int storyPoint;
}
