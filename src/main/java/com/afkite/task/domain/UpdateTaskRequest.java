package com.afkite.task.domain;

import java.time.LocalDate;

import com.afkite.task.domain.entity.TaskPriority;
import com.afkite.task.domain.entity.TaskStatus;

/*
 * Models a request to update an existing task.
 * This class is owned by the service layer.
 * 
 * @param title       - The title of the task.
 * @param description - The description of the task.
 * @param dueDate     - The date the task is due.
 * @param status      - The status of the task. Can be null.
 * @param priority    - The priority of the task.
 */
public record UpdateTaskRequest(
    String title,
    String description,
    LocalDate dueDate,
    TaskStatus status,
    TaskPriority priority
) {
    
}
