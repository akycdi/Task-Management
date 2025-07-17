package com.airtribe.task.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.airtribe.task.dto.TaskRequestDto;
import com.airtribe.task.dto.TaskResponseDto;
import com.airtribe.task.entity.Task;
import com.airtribe.task.exception.TaskNotFoundException;
import com.airtribe.task.repository.TaskRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public TaskResponseDto createTask(TaskRequestDto dto) {
        Task task = mapToEntity(dto);
        return mapToDto(repository.save(task));
    }

    public TaskResponseDto getTask(Long id) {
        try {
            Task task = repository.findById(id)
                    .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));
            return mapToDto(task);
        } catch (TaskNotFoundException ex) {
            log.warn("Task not found in getTask(): {}", ex.getMessage());
            throw ex;
        }
    }

    public List<TaskResponseDto> getAllTasks() {
        return repository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public TaskResponseDto updateTask(Long id, TaskRequestDto dto) {
        try {
            Task task = findById(id);
            task.setTitle(dto.getTitle());
            task.setDescription(dto.getDescription());
            task.setStatus(dto.getStatus());
            task.setPriority(dto.getPriority());
            task.setDueDate(dto.getDueDate());
            return mapToDto(repository.save(task));
        } catch (TaskNotFoundException ex) {
            log.warn("Update failed. Task not found with id: {}", id);
            throw ex;
        }
    }

    public void deleteTask(Long id) {
        try {
            Task task = findById(id);
            repository.delete(task);
            log.info("Deleted task with id: {}", id);
        } catch (TaskNotFoundException ex) {
            log.warn("Delete failed. Task not found with id: {}", id);
            throw ex;
        }
    }

    private Task findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));
    }

    private Task mapToEntity(TaskRequestDto dto) {
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        task.setPriority(dto.getPriority());
        task.setDueDate(dto.getDueDate());
        return task;
    }

    private TaskResponseDto mapToDto(Task task) {
        TaskResponseDto dto = new TaskResponseDto();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus());
        dto.setPriority(task.getPriority());
        dto.setDueDate(task.getDueDate());
        return dto;
    }

}