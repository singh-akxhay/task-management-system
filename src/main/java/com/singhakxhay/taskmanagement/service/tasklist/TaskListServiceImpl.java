package com.singhakxhay.taskmanagement.service.tasklist;

import com.singhakxhay.taskmanagement.data.models.TaskListDb;
import com.singhakxhay.taskmanagement.data.repository.TaskListRepository;
import com.singhakxhay.taskmanagement.exception.tasklist.TaskListAlreadyExistsException;
import com.singhakxhay.taskmanagement.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskListServiceImpl implements TaskListService {
  private final TaskListRepository taskListRepository;
  private final UserService userService;

  @Override
  public List<TaskListDb> getTaskListsByUserId(String userId) {
    var user = userService.getUserById(userId);
    return taskListRepository.findByCreatedBy(user);
  }

  @Override
  public TaskListDb createTaskList(String userId, TaskListDb taskList) {
    // Find user
    var user = userService.getUserById(userId);

    // Check if task list already exists
    var nameExists = taskListRepository.existsByName(taskList.getName());
    if (nameExists) {
      throw new TaskListAlreadyExistsException("TaskList=%s already exists".formatted(taskList.getName()));
    }

    // Assign task list to the given user
    taskList.setCreatedBy(user);

    // Create task list
    return taskListRepository.save(taskList);
  }
}
