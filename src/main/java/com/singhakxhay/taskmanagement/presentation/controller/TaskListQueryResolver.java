package com.singhakxhay.taskmanagement.presentation.controller;

import com.singhakxhay.taskmanagement.presentation.models.tasklist.TaskList;
import com.singhakxhay.taskmanagement.presentation.models.tasklist.TaskListInput;
import com.singhakxhay.taskmanagement.service.mapper.TaskListMapper;
import com.singhakxhay.taskmanagement.service.tasklist.TaskListService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaskListQueryResolver {
  private final TaskListService taskListService;

  @QueryMapping
  private List<TaskList> taskListsByUserID(@Argument String userId) {
    var taskLists = taskListService.getTaskListsByUserId(userId);
    return TaskListMapper.getInstance().mapToTaskLists(taskLists);
  }

  @MutationMapping
  private TaskList createTaskList(@Argument String userId, @Argument TaskListInput taskListInput) {
    var taskList = taskListService.createTaskList(
        userId,
        TaskListMapper.getInstance().mapToTaskListDb(taskListInput)
    );

    return TaskListMapper.getInstance().mapToTaskList(taskList);
  }
}

