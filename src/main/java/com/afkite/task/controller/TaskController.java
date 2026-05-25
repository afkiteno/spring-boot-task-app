package com.afkite.task.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.afkite.task.domain.CreateTaskRequest;
import com.afkite.task.domain.UpdateTaskRequest;
import com.afkite.task.domain.dto.CreateTaskRequestDto;
import com.afkite.task.domain.dto.TaskDto;
import com.afkite.task.domain.dto.UpdateTaskRequestDto;
import com.afkite.task.domain.entity.Task;
import com.afkite.task.domain.mapper.TaskMapper;
import com.afkite.task.service.TaskService;

import jakarta.validation.Valid;

/* REST API controller for tasks. */
@RestController
@RequestMapping(path = "/api/v1/tasks")
public class TaskController {
    
    /* The task service from the service layer. */
    private final TaskService taskService;

    /* The task mapper. */
    private final TaskMapper taskMapper;
    
    /*
     * Constructs a new TaskController.
     * 
     * @param taskService - The TaskService dependency.
     * @param taskMapper  - The TaskMapper dependency.
     */
    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    /*
     * Creates a new task.
     *
     * @param createTaskRequestDto - The request DTO used to create a task.
     * @return                     - A representation of the created task and an HTTP 201 CREATED.
     */
    @PostMapping
    public ResponseEntity<TaskDto> createTask(
        @Valid @RequestBody CreateTaskRequestDto createTaskRequestDto
    ) {

        // Map the CreateTaskRequestDto to a CreateTaskReqest.
        CreateTaskRequest createTaskRequest = taskMapper.fromDto(createTaskRequestDto);

        // Call createTask on the TaskService,
        // passing the CreateTaskRequest as an argument.
        Task task = taskService.createTask(createTaskRequest);

        // Map the newly created Task object into a TaskDto.
        TaskDto createdTaskDto = taskMapper.toDto(task);

        // Return the TaskDto object to the caller with an HTTP 201 CREATED.
        return new ResponseEntity<>(createdTaskDto, HttpStatus.CREATED);
    }

    /*
     * Lists all tasks with an HTTP 200 OK.
     *
     * @return - The list of tasks.
     */
    @GetMapping
    public ResponseEntity<List<TaskDto>> listTasks() {

        // Call the listTasks method on the TaskService to get a list of tasks.
        List<Task> tasks = taskService.listTasks();

        // Map the list of Task objects to a list of TaskDto objects.
        List<TaskDto> taskDtos = tasks.stream().map(taskMapper::toDto).toList();

        // Return the list of TaskDto objects with an HTTP 200 OK.
        return ResponseEntity.ok(taskDtos);
    }

    /*
     * Updates the specified task with the provided information.
     * 
     * @param taskId               - The ID of the task to update.
     * @param updateTaskRequestDto - The request DTO used to update the task.
     * @return                     - A presentation of the updated task with an HTTP 200.
     */
    @PutMapping(path = "/{taskId}")
    public ResponseEntity<TaskDto> updateTask(
        @PathVariable UUID taskId,
        @Valid @RequestBody UpdateTaskRequestDto updateTaskRequestDto
    ) {

        // Map the UpdateTaskRequestDto to an UpdateTaskRequest.
        UpdateTaskRequest updateTaskRequest = taskMapper.fromDto(updateTaskRequestDto);

        // Pass the UpdateTakeRequest to the TaskService's updateTask method.
        Task task = taskService.updateTask(taskId, updateTaskRequest);

        // Map the Task to a TaskDto.
        TaskDto updatedTaskDto = taskMapper.toDto(task);

        // Return the TaskDto with an HTTP 200.
        return ResponseEntity.ok(updatedTaskDto);
    }

    /*
     * Deletes the specified Task.
     * Always returns an HTTP 204 NO CONTENT.
     *
     * @param taskId - The ID of the task.
     * @return       - AN HTTP 204 NO CONTENT.
     */
    @DeleteMapping(path = "/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID taskId) {

        // Calls the deleteTask method on the TaskService.
        taskService.deleteTask(taskId);

        // Return an HTTP 204 NO CONTENT.
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
