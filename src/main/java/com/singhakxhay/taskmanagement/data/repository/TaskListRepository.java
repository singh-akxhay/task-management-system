package com.singhakxhay.taskmanagement.data.repository;

import com.singhakxhay.taskmanagement.data.models.TaskListDb;
import com.singhakxhay.taskmanagement.data.models.UserDb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskListRepository extends JpaRepository<TaskListDb, String> {
  List<TaskListDb> findByCreatedBy(UserDb createdBy);
}
