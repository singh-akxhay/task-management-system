package com.singhakxhay.taskmanagement.data.repository;

import com.singhakxhay.taskmanagement.data.models.TaskDb;
import com.singhakxhay.taskmanagement.data.models.TaskStatusDb;
import com.singhakxhay.taskmanagement.data.models.UserDb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskDb, String> {
  List<TaskDb> findByCreatedBy(UserDb createdBy);

  List<TaskDb> findByCreatedByAndTaskStatus(UserDb createdBy, TaskStatusDb taskStatus);
}
