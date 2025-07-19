package com.airtribe.task.controller;

import com.airtribe.task.dto.TaskRequestDto;
import com.airtribe.task.dto.TaskResponseDto;
import com.airtribe.task.service.TaskService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTest {
    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    @Test
    void testCreateTask_success() {
        TaskRequestDto request = new TaskRequestDto();
        TaskResponseDto response = new TaskResponseDto();
        when(taskService.createTask(request)).thenReturn(response);
        ResponseEntity<TaskResponseDto> result = taskController.createTask(request);
        assertEquals(201, result.getStatusCode().value());
        assertEquals(response, result.getBody());
    }

    @Test
    void testCreateTask_failure() {
        TaskRequestDto request = new TaskRequestDto();
        when(taskService.createTask(request)).thenThrow(new RuntimeException("Error"));
        assertThrows(RuntimeException.class, () -> taskController.createTask(request));
    }

    @Test
    void testGetTask_success() {
        TaskResponseDto response = new TaskResponseDto();
        when(taskService.getTask(1L)).thenReturn(response);
        ResponseEntity<TaskResponseDto> result = taskController.getTask(1L);
        assertEquals(200, result.getStatusCode().value());
        assertEquals(response, result.getBody());
    }

    @Test
    void testGetTask_failure() {
        when(taskService.getTask(1L)).thenThrow(new RuntimeException("Not found"));
        assertThrows(RuntimeException.class, () -> taskController.getTask(1L));
    }

    @Test
    void testGetAllTasks_success() {
        List<TaskResponseDto> list = Arrays.asList(new TaskResponseDto(), new TaskResponseDto());
        when(taskService.getAllTasks()).thenReturn(list);
        ResponseEntity<List<TaskResponseDto>> result = taskController.getAllTasks();
        assertEquals(200, result.getStatusCode().value());
        assertEquals(list, result.getBody());
    }

    @Test
    void testGetAllTasks_failure() {
        when(taskService.getAllTasks()).thenThrow(new RuntimeException("Error"));
        assertThrows(RuntimeException.class, () -> taskController.getAllTasks());
    }

    @Test
    void testUpdateTask_success() {
        TaskRequestDto request = new TaskRequestDto();
        TaskResponseDto response = new TaskResponseDto();
        when(taskService.updateTask(1L, request)).thenReturn(response);
        ResponseEntity<TaskResponseDto> result = taskController.updateTask(1L, request);
        assertEquals(200, result.getStatusCode().value());
        assertEquals(response, result.getBody());
    }

    @Test
    void testUpdateTask_failure() {
        TaskRequestDto request = new TaskRequestDto();
        when(taskService.updateTask(1L, request)).thenThrow(new RuntimeException("Update failed"));
        assertThrows(RuntimeException.class, () -> taskController.updateTask(1L, request));
    }

    @Test
    void testDeleteTask_success() {
        doNothing().when(taskService).deleteTask(1L);
        ResponseEntity<Void> result = taskController.deleteTask(1L);
        assertEquals(204, result.getStatusCode().value());
        assertNull(result.getBody());
    }

    @Test
    void testDeleteTask_failure() {
        doThrow(new RuntimeException("Delete failed")).when(taskService).deleteTask(1L);
        assertThrows(RuntimeException.class, () -> taskController.deleteTask(1L));
    }
}
