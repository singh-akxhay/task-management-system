package com.singhakxhay.taskmanagement.presentation.controller;

import com.singhakxhay.taskmanagement.presentation.models.task.Task;
import com.singhakxhay.taskmanagement.presentation.models.task.TaskInput;
import com.singhakxhay.taskmanagement.service.mapper.TaskMapper;
import com.singhakxhay.taskmanagement.service.task.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaskQueryController {
  private final TaskService taskService;

  @QueryMapping
  private List<Task> tasksByUserId(@Argument String userId) {
    var tasks = taskService.getTasksByUserId(userId);
    return TaskMapper.getInstance().mapToTaskList(tasks);
  }

  @QueryMapping
  private Task createTask(@Argument String userId, @Argument TaskInput taskInput, @Argument String taskListId) {
    var task = taskService.createTask(
        userId,
        TaskMapper.getInstance().mapToTaskDb(taskInput),
        taskListId
    );

    return TaskMapper.getInstance().mapToTask(task);
  }
}
