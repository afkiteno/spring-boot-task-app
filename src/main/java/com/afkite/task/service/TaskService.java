package com.afkite.task.service;

import java.util.List;
import java.util.UUID;

import com.afkite.task.domain.CreateTaskRequest;
import com.afkite.task.domain.UpdateTaskRequest;
import com.afkite.task.domain.entity.Task;

/* Service for handling Tasks. */
public interface TaskService {

    /*
     * Creates a new task.
     * 
     * @param request - The request object used to create the task.
     * @return        - The created task.
     */
    Task createTask(CreateTaskRequest request);

    /*
     * Lists all tasks.
     *
     * @return        - A list of all tasks.
     */
    List<Task> listTasks();

    /*
     * Updates the specified task.
     *
     * @param taskId  - The ID of the task to update.
     * @param request - The request object used to update the task.
     * @return        - The updated task.
     */
    Task updateTask(UUID taskId, UpdateTaskRequest request);

    /*
     * Deletes the specified task.
     * Does not throw an exception when the specified task does not exist.
     * 
     * @param taskId - The ID of the task to delete.
     */
    void deleteTask(UUID taskId);
}
