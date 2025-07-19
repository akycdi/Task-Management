package com.airtribe.task.service;

import com.airtribe.task.dto.TaskRequestDto;
import com.airtribe.task.dto.TaskResponseDto;
import com.airtribe.task.entity.Task;
import com.airtribe.task.exception.TaskNotFoundException;
import com.airtribe.task.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {
    @Mock
    private TaskRepository repository;

    @InjectMocks
    private TaskService service;

    @Test
    void testCreateTask_success() {
        TaskRequestDto dto = new TaskRequestDto();
        Task task = new Task();
        when(repository.save(any(Task.class))).thenReturn(task);
        TaskResponseDto result = service.createTask(dto);
        assertNotNull(result);
    }

    @Test
    void testGetTask_success() {
        Task task = new Task();
        when(repository.findById(1L)).thenReturn(Optional.of(task));
        TaskResponseDto result = service.getTask(1L);
        assertNotNull(result);
    }

    @Test
    void testGetTask_failure() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(TaskNotFoundException.class, () -> service.getTask(1L));
    }

    @Test
    void testGetAllTasks_success() {
        Task task1 = new Task();
        Task task2 = new Task();
        when(repository.findAll()).thenReturn(Arrays.asList(task1, task2));
        List<TaskResponseDto> result = service.getAllTasks();
        assertEquals(2, result.size());
    }

    @Test
    void testUpdateTask_success() {
        TaskRequestDto dto = new TaskRequestDto();
        Task task = new Task();
        when(repository.findById(1L)).thenReturn(Optional.of(task));
        when(repository.save(any(Task.class))).thenReturn(task);
        TaskResponseDto result = service.updateTask(1L, dto);
        assertNotNull(result);
    }

    @Test
    void testUpdateTask_failure() {
        TaskRequestDto dto = new TaskRequestDto();
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(TaskNotFoundException.class, () -> service.updateTask(1L, dto));
    }

    @Test
    void testDeleteTask_success() {
        Task task = new Task();
        when(repository.findById(1L)).thenReturn(Optional.of(task));
        doNothing().when(repository).delete(task);
        assertDoesNotThrow(() -> service.deleteTask(1L));
    }

    @Test
    void testDeleteTask_failure() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(TaskNotFoundException.class, () -> service.deleteTask(1L));
    }
}
