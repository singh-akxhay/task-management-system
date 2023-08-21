package com.singhakxhay.taskmanagement.service.tasklist;

import com.singhakxhay.taskmanagement.data.models.TaskListDb;
import com.singhakxhay.taskmanagement.data.repository.TaskListRepository;
import com.singhakxhay.taskmanagement.exception.tasklist.TaskListAlreadyExistsException;
import com.singhakxhay.taskmanagement.exception.tasklist.TaskListNotFoundException;
import com.singhakxhay.taskmanagement.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskListServiceImpl implements TaskListService {
  private final TaskListRepository taskListRepository;
  private final UserService userService;

  @Override
  public List<TaskListDb> getTaskListsByUserId(String userId) {
    log.info("[getTaskListsByUserId] - Started fetching task lists for userId = {}", userId);

    var user = userService.getUserById(userId);
    var taskLists = user.getTaskLists();

    log.info("[getTaskListsByUserId] - Ended fetching task lists for userId = {}", userId);

    return taskLists;
  }

  @Override
  public TaskListDb createTaskList(String userId, TaskListDb taskList) {
    log.info("[createTaskList] - Started creating task list for userId = {} and taskList = {}", userId, taskList);

    // Find user
    var user = userService.getUserById(userId);

    // Check if task list already exists
    var nameExists = taskListRepository.existsByName(taskList.getName());
    if (Boolean.TRUE.equals(nameExists)) {
      throw new TaskListAlreadyExistsException("TaskList=%s already exists".formatted(taskList.getName()));
    }

    // Assign task list to the given user
    taskList.setCreatedBy(user);

    // Create task list
    var save = taskListRepository.save(taskList);

    log.info("[createTaskList] - Ended creating task list for userId = {} and taskListId = {}", userId, save.getTaskListId());

    return save;
  }

  @Override
  public TaskListDb getTaskListByTaskListId(String userId, String taskListId) {
    log.info("[getTaskListByTaskListId] - Started fetching task list for userId = {} and taskListId = {}", userId, taskListId);

    // Find task list
    var taskList = taskListRepository.findById(taskListId)
        .orElseThrow(() -> new TaskListNotFoundException("TaskListId=%s not found".formatted(taskListId)));

    // Check if task list belongs to given user id or not
    if (!taskList.getCreatedBy().getUserId().equals(userId)) {
      throw new UnsupportedOperationException("Task list is not mapped to given user ID");
    }

    log.info("[getTaskListByTaskListId] - Ended fetching task list for userId = {} and taskListId = {}", userId, taskListId);

    return taskList;
  }
}
