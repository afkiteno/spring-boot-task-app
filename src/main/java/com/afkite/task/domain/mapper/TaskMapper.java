package com.afkite.task.domain.mapper;

import com.afkite.task.domain.CreateTaskRequest;
import com.afkite.task.domain.UpdateTaskRequest;
import com.afkite.task.domain.dto.CreateTaskRequestDto;
import com.afkite.task.domain.dto.TaskDto;
import com.afkite.task.domain.dto.UpdateTaskRequestDto;
import com.afkite.task.domain.entity.Task;

/* Mapper handling Tasks. */
public interface TaskMapper {
    CreateTaskRequest fromDto(CreateTaskRequestDto dto);

    UpdateTaskRequest fromDto (UpdateTaskRequestDto dto);

    TaskDto toDto(Task task);
}
