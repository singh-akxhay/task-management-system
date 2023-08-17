package com.singhakxhay.taskmanagement.data.repository;

import com.singhakxhay.taskmanagement.data.models.TaskStatusDb;
import com.singhakxhay.taskmanagement.data.models.UserDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.config.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, String> {
  List<Task> findByCreatedBy(UserDb createdBy);

  List<Task> findByCreatedByAndTaskStatus(UserDb createdBy, TaskStatusDb taskStatus);
}
