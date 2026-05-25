package com.afkite.task.domain;

import java.time.LocalDate;

import com.afkite.task.domain.entity.TaskPriority;

/*
 * Models a request to create a new task.
 * This class is owned by the service layer.
 * 
 * @param title       - The title of the task to create.
 * @param description - The description of the task to create.
 * @param dueDate     - The date the task is due. Can be null.
 * @param priority    - The priority of the task.
 */
public record CreateTaskRequest(
    String title,
    String description,
    LocalDate dueDate,
    TaskPriority priority
) {}
