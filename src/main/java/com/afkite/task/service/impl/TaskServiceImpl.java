package com.afkite.task.service.impl;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.afkite.task.domain.CreateTaskRequest;
import com.afkite.task.domain.UpdateTaskRequest;
import com.afkite.task.domain.entity.Task;
import com.afkite.task.domain.entity.TaskStatus;
import com.afkite.task.exception.TaskNotFoundException;
import com.afkite.task.repository.TaskRepository;
import com.afkite.task.service.TaskService;

/* Service for handling Tasks. */
@Service
public class TaskServiceImpl implements TaskService {

    /* The task repository. */
    private final TaskRepository taskRepository;

    /*
     * Constructs a new TaskServiceImpl using the provided values.
     *
     * @param taskRepository - The TaskRepository dependency.
     */
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task createTask(CreateTaskRequest request) {

        // Get the time, date, and timezone right now.
        Instant now = Instant.now();

        // Create a new Task entity.
        Task task = new Task(
                null, // Hibernate to generate an ID for us.
                request.title(),
                request.description(),
                request.dueDate(),
                TaskStatus.OPEN, // Default to an open status.
                request.priority(),
                now,
                now);

        // Save the Task, returning the saved Task to the caller.
        return taskRepository.save(task);
    }

    @Override
    public List<Task> listTasks() {
        return taskRepository.findAll(Sort.by(Direction.ASC, "created"));
    }

    @Override
    public Task updateTask(UUID taskId, UpdateTaskRequest request) {

        // Look up the existing task. If it doesn't exist
        // throw a TaskNotFoundException
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));

        // Update the existing task with the provided information.
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setDueDate(request.dueDate());
        task.setStatus(request.status());
        task.setPriority(request.priority());

        // Update the existing task's updated value.
        task.setUpdated(Instant.now());

        // Save the existing task.
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(UUID taskId) {
        taskRepository.deleteById(taskId);
    }

}
