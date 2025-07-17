package com.airtribe.task.dto;

import java.time.LocalDate;

import com.airtribe.task.enums.TaskPriority;
import com.airtribe.task.enums.TaskStatus;

import lombok.Data;

@Data
public class TaskResponseDto {
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private LocalDate dueDate;
}