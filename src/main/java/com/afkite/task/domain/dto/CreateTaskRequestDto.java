package com.afkite.task.domain.dto;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import com.afkite.task.domain.entity.TaskPriority;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/*
 * A DTO modelling a request to create a new task.
 * This class is owned by the presentation layer.
 * 
 * @param title       - The title of the task to create.
 * @param description - The description of the task to create.
 * @param dueDate     - The date the task is due. Can be null.
 * @param priority    - The priority of the task.
 */
public record CreateTaskRequestDto(
    @NotBlank(message = ERROR_MESSAGE_TITLE_LENGTH)
    @Length(max = 255, message = ERROR_MESSAGE_TITLE_LENGTH)
    String title,

    @Length(max = 1000, message = ERROR_MESSAGE_DESCRIPTION_LENGTH)
    @Nullable
    String description,

    @FutureOrPresent(message = ERROR_MESSAGE_DUE_DATE_FUTURE)
    @Nullable
    LocalDate dueDate,

    @NotNull(message = ERROR_MESSAGE_PRIORITY)
    TaskPriority priority
) {
    private static final String ERROR_MESSAGE_TITLE_LENGTH = "Title must be between 1 and 255 characters";
    private static final String ERROR_MESSAGE_DESCRIPTION_LENGTH = "Description must be less than 1000 characters";
    private static final String ERROR_MESSAGE_DUE_DATE_FUTURE = "Due date must be in the future";
    private static final String ERROR_MESSAGE_PRIORITY = "Task priority must be provided";

}
