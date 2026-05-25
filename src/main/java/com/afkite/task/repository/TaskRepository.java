package com.afkite.task.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.afkite.task.domain.entity.Task;

/* Repository for handling tasks. */
@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {}
