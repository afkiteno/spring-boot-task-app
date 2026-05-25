package com.afkite.task.domain.dto;

import java.time.LocalDate;
import java.util.UUID;

import com.afkite.task.domain.entity.TaskPriority;
import com.afkite.task.domain.entity.TaskStatus;

/*
 * A DTO representating a task. This class is owned by the presentation layer.
 * 
 * @param id          - The ID of the task.
 * @param title       - The title of the task.
 * @param description - The description of the task.
 * @param dueDate     - The date the task is due.
 * @param priority    - The priority of the task.
 * @param status      - The status of the task.
 */
public record TaskDto(
    UUID id,
    String title,
    String description,
    LocalDate dueDate,
    TaskPriority priority,
    TaskStatus status
) {
    
}
