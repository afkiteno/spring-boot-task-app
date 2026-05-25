package com.afkite.task.domain.mapper.impl;

import org.springframework.stereotype.Component;

import com.afkite.task.domain.CreateTaskRequest;
import com.afkite.task.domain.UpdateTaskRequest;
import com.afkite.task.domain.dto.CreateTaskRequestDto;
import com.afkite.task.domain.dto.TaskDto;
import com.afkite.task.domain.dto.UpdateTaskRequestDto;
import com.afkite.task.domain.entity.Task;
import com.afkite.task.domain.mapper.TaskMapper;

@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public CreateTaskRequest fromDto(CreateTaskRequestDto dto) {
        return new CreateTaskRequest(
            dto.title(),
            dto.description(),
            dto.dueDate(),
            dto.priority()
        );
    }

    @Override
    public UpdateTaskRequest fromDto(UpdateTaskRequestDto dto) {
        return new UpdateTaskRequest(
            dto.title(),
            dto.description(),
            dto.dueDate(),
            dto.status(),
            dto.priority()
        );
    }
    
    @Override
    public TaskDto toDto(Task task) {
        return new TaskDto(
            task.getId(),
            task.getTitle(),
            task.getDescription(),
            task.getDueDate(),
            task.getPriority(),
            task.getStatus()
        );
    }    
}
