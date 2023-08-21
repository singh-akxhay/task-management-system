package com.singhakxhay.taskmanagement.presentation.controller;

import com.singhakxhay.taskmanagement.presentation.models.tasklist.TaskList;
import com.singhakxhay.taskmanagement.presentation.models.tasklist.TaskListInput;
import com.singhakxhay.taskmanagement.service.mapper.TaskListMapper;
import com.singhakxhay.taskmanagement.service.tasklist.TaskListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TaskListQueryResolver {
  private final TaskListService taskListService;

  @QueryMapping
  private List<TaskList> taskListsByUserId(@Argument String userId) {
    log.info("[taskListsByUserId] - Started fetching task lists for userId = {}", userId);

    var taskLists = TaskListMapper.getInstance()
        .mapToTaskLists(taskListService.getTaskListsByUserId(userId));

    log.info("[taskListsByUserId] - Started fetching task lists for userId = {}", userId);

    return taskLists;
  }

  @MutationMapping
  private TaskList createTaskList(@Argument String userId, @Argument TaskListInput taskListInput) {
    log.info("[createTaskList] - Started creating task list for userId = {} and taskListInput = {}", userId, taskListInput);

    var taskList = TaskListMapper.getInstance()
        .mapToTaskList(
            taskListService.createTaskList(
                userId,
                TaskListMapper.getInstance().mapToTaskListDb(taskListInput)
            )
        );

    log.info("[createTaskList] - Ended creating task list for userId = {} and taskListId = {}", userId, taskList.getTaskListId());

    return taskList;
  }
}

