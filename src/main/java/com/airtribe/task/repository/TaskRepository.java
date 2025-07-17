package com.airtribe.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airtribe.task.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}