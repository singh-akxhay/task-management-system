package com.singhakxhay.taskmanagement.presentation.controller;

import com.singhakxhay.taskmanagement.presentation.models.task.Task;
import com.singhakxhay.taskmanagement.presentation.models.task.TaskInput;
import com.singhakxhay.taskmanagement.service.mapper.TaskMapper;
import com.singhakxhay.taskmanagement.service.task.TaskService;
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
public class TaskQueryController {
  private final TaskService taskService;
  private final TaskListService taskListService;

  @QueryMapping
  private List<Task> tasksByUserId(@Argument String userId) {
    log.info("[tasksByUserId] - Started fetching tasks for userId = {}", userId);

    var tasks = TaskMapper.getInstance()
        .mapToTaskList(taskService.getTasksByUserId(userId));

    log.info("[tasksByUserId] - Ended fetching tasks for userId = {}", userId);

    return tasks;
  }

  @MutationMapping
  private Task createTask(@Argument String userId, @Argument TaskInput taskInput, @Argument String taskListId) {
    log.info("[createTask] - Started creating task for userId = {}, taskListId = {} and taskInput = {}", userId, taskListId, taskInput);

    var task = TaskMapper.getInstance()
        .mapToTask(
            taskService.createTask(
                userId,
                TaskMapper.getInstance().mapToTaskDb(taskInput),
                taskListId
            )
        );

    log.info("[createTask] - Ended creating task for userId = {}, taskListId = {} and taskId = {}", userId, taskListId, task.getTaskId());

    return task;
  }
}
