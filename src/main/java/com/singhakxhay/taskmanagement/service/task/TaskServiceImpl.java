package com.singhakxhay.taskmanagement.service.task;

import com.singhakxhay.taskmanagement.data.models.TaskDb;
import com.singhakxhay.taskmanagement.data.repository.TaskRepository;
import com.singhakxhay.taskmanagement.service.tasklist.TaskListService;
import com.singhakxhay.taskmanagement.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
  private final TaskRepository taskRepository;
  private final UserService userService;
  private final TaskListService taskListService;

  @Override
  public List<TaskDb> getTasksByUserId(String userId) {
    var user = userService.getUserById(userId);
    return user.getTasks();
  }

  @Override
  public TaskDb createTask(String userId, TaskDb task, String taskListId) {
    // Find user
    var user = userService.getUserById(userId);

    // Find task list
    var taskList = taskListService.getTaskListByTaskListId(userId, taskListId);

    // Assign user and task list to the provided task
    task.setCreatedBy(user);
    task.setTaskList(taskList);

    // Create task
    return taskRepository.save(task);
  }
}
