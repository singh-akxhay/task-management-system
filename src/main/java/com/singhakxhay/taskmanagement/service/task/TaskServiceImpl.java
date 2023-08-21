package com.singhakxhay.taskmanagement.service.task;

import com.singhakxhay.taskmanagement.data.models.TaskDb;
import com.singhakxhay.taskmanagement.data.models.TaskStatusDb;
import com.singhakxhay.taskmanagement.data.repository.TaskRepository;
import com.singhakxhay.taskmanagement.service.tasklist.TaskListService;
import com.singhakxhay.taskmanagement.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {
  private final TaskRepository taskRepository;
  private final UserService userService;
  private final TaskListService taskListService;

  @Override
  public List<TaskDb> getTasksByUserId(String userId) {
    log.info("[getTasksByUserId] - Started fetching tasks for userId = {}", userId);

    var user = userService.getUserById(userId);
    var tasks = user.getTasks();

    log.info("[getTasksByUserId] - Ended fetching tasks for userId = {}", userId);

    return tasks;
  }

  @Override
  public TaskDb createTask(String userId, TaskDb task, String taskListId) {
    log.info("[createTask] - Started creating task for userId = {}, taskListId = {} and task = {}", userId, taskListId, task);

    // Find user
    var user = userService.getUserById(userId);

    // Find task list
    var taskList = taskListService.getTaskListByTaskListId(userId, taskListId);

    // Assign user and task list to the provided task
    task.setCreatedBy(user);
    task.setTaskList(taskList);
    task.setTaskStatus(TaskStatusDb.NEW);

    // Create task
    var save = taskRepository.save(task);

    log.info("[createTask] - Ended creating task for userId = {}, taskListId = {} and taskId = {}", userId, taskListId, save.getTaskId());

    return save;
  }
}
