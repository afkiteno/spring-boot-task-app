package com.afkite.task.exception;

import java.util.UUID;

/* Thrown when a task is not found. */
public class TaskNotFoundException extends RuntimeException {

    /* The ID of the task that was not found. */
    private final UUID id;

    /*
     * Constructs a new TaskNotFoundException using
     * the ID of the task not found.
     * 
     * @param id - The ID of the task not found.
     */
    public TaskNotFoundException(UUID id) {
        super(String.format("Task with ID '%s' does not exist", id));
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
