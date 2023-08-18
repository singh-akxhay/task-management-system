package com.singhakxhay.taskmanagement.presentation.controller;

import com.singhakxhay.taskmanagement.presentation.models.task.Task;
import com.singhakxhay.taskmanagement.presentation.models.task.TaskInput;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskQueryController {
  @QueryMapping
  private List<Task> tasksByUserId(@Argument String userId) {
    return List.of();
  }

  @QueryMapping
  private Task createTask(@Argument String userId, @Argument TaskInput taskInput, @Argument String taskListId) {
    return null;
  }
}
