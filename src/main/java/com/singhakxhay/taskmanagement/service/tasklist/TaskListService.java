package com.singhakxhay.taskmanagement.service.tasklist;

import com.singhakxhay.taskmanagement.data.models.TaskListDb;

import java.util.List;

public interface TaskListService {

  List<TaskListDb> getTaskListsByUserId(String userId);

  TaskListDb createTaskList(String userId, TaskListDb taskList);
}
