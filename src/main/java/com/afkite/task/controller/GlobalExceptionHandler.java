package com.afkite.task.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.afkite.task.domain.dto.ErrorDto;
import com.afkite.task.exception.TaskNotFoundException;

/*
 * Handles exceptions thrown by the service layer,
 * returning errors in a standardised format.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /*
     * Handles MethodArgumentNotValidException,
     * returning a standardised error response and an HTTP 400 BAD REQUEST.
     * This exception is thrown when a @Valid validation fails.
     * 
     * @param ex - The exception.
     * @return   - A standardised error response.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleValidationExceptions(MethodArgumentNotValidException ex) {

        // Get the first validation error message
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(x -> x.getDefaultMessage())
                .orElse("Validation Failed.");

        // Create an ErrorDto using the error message.
        ErrorDto errorDto = new ErrorDto(errorMessage);

        // Return the ErrorDto in the response body
        // with an HTTP 400 BAD REQUEST.
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    /*
     * Handles the TaskNotFoundException, returning a standardised error
     * response and an HTTP 400.
     * This exception is thrown when the specified task is not found.
     * 
     * @param ex - The TaskNotFoundException.
     * @return   - The standardised error and an HTTP 400.
     */    
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorDto> handleTaskNotFoundException(TaskNotFoundException ex) {
        UUID taskNotFoundId = ex.getId();
        String errorMessage = String.format("Task with ID '%s' not found.", taskNotFoundId);
        ErrorDto errorDto = new ErrorDto(errorMessage);

        // Return the ErrorDto with status HTTP 400 BAD REQUEST.
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }
}
