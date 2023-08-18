package com.singhakxhay.taskmanagement.presentation.controller;

import com.singhakxhay.taskmanagement.presentation.models.tasklist.TaskList;
import com.singhakxhay.taskmanagement.presentation.models.tasklist.TaskListInput;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskListQueryResolver {
  @QueryMapping
  private List<TaskList> taskListsByUserID(@Argument String userId) {
    return List.of();
  }

  @MutationMapping
  private TaskList createTaskList(@Argument String userId, @Argument TaskListInput taskListInput) {
    return null;
  }
}

