package com.airtribe.task.dto;


import com.airtribe.task.enums.TaskPriority;
import com.airtribe.task.enums.TaskStatus;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskRequestDto {
    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private LocalDate dueDate;
}