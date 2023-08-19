package com.singhakxhay.taskmanagement.service.task;

import com.singhakxhay.taskmanagement.data.models.TaskDb;

import java.util.List;

public interface TaskService {
  List<TaskDb> getTasksByUserId(String userId);

  TaskDb createTask(String userId, TaskDb task, String taskListId);
}
