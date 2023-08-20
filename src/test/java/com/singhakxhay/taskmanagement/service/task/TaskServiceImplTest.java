package com.singhakxhay.taskmanagement.service.task;

import com.singhakxhay.taskmanagement.data.models.TaskDb;
import com.singhakxhay.taskmanagement.data.models.TaskListDb;
import com.singhakxhay.taskmanagement.data.models.UserDb;
import com.singhakxhay.taskmanagement.data.repository.TaskRepository;
import com.singhakxhay.taskmanagement.exception.tasklist.TaskListNotFoundException;
import com.singhakxhay.taskmanagement.exception.user.UserNotFoundException;
import com.singhakxhay.taskmanagement.service.tasklist.TaskListService;
import com.singhakxhay.taskmanagement.service.user.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {
  @InjectMocks
  private TaskServiceImpl mockTaskService;

  @Mock
  private TaskRepository mockTaskRepository;

  @Mock
  private UserService mockUserService;

  @Mock
  private TaskListService mockTaskListService;

  @Test
  void givenValidUserId_thenGetTasksByUserIdIsCalled_thenReturnTasks() {
    var userId = UUID.randomUUID().toString();

    var user = UserDb.builder()
        .userId(userId)
        .name("Akshay")
        .username("akshay")
        .password("test-password-1")
        .createdAt(Date.valueOf("2023-08-19"))
        .modifiedAt(Date.valueOf("2023-08-19"))
        .build();

    var task1 = TaskDb.builder()
        .taskId(UUID.randomUUID().toString())
        .title("Complete GraphQL project")
        .description("A mini project to demonstrate how to use GraphQL with Spring boot.")
        .dueDate(Date.valueOf("2023-08-26"))
        .createdAt(Date.valueOf("2023-08-20"))
        .modifiedAt(Date.valueOf("2023-08-20"))
        .build();

    var task2 = TaskDb.builder()
        .taskId(UUID.randomUUID().toString())
        .title("Create new project on next topic")
        .description("Create new project in github with proper milestone and tasks")
        .dueDate(Date.valueOf("2023-08-20"))
        .createdAt(Date.valueOf("2023-08-19"))
        .modifiedAt(Date.valueOf("2023-08-19"))
        .build();

    var tasks = List.of(task1, task2);
    user.setTasks(tasks);

    Mockito.when(mockUserService.getUserById(userId))
        .thenReturn(user);


    var actual = mockTaskService.getTasksByUserId(userId);

    Assertions.assertEquals(tasks.size(), actual.size());

    for (int i = 0; i < actual.size(); i++) {
      Assertions.assertEquals(tasks.get(i).getTaskId(), actual.get(i).getTaskId());
    }
  }

  @Test
  void givenInvalidUserId_thenGetTasksByUserIdIsCalled_thenThrowException() {
    var userId = UUID.randomUUID().toString();

    Mockito.when(mockUserService.getUserById(userId))
        .thenThrow(UserNotFoundException.class);

    Assertions.assertThrows(UserNotFoundException.class, () -> mockTaskService.getTasksByUserId(userId));
  }

  @Test
  void givenValidUserIdAndValidTaskListIdAndTaskData_thenCreateTaskIsCalled_thenReturnTask() {
    var userId = UUID.randomUUID().toString();
    var taskListId = UUID.randomUUID().toString();

    var user = UserDb.builder()
        .userId(userId)
        .name("Akshay")
        .username("akshay")
        .password("test-password-1")
        .createdAt(Date.valueOf("2023-08-19"))
        .modifiedAt(Date.valueOf("2023-08-19"))
        .build();

    var taskList = TaskListDb.builder()
        .taskListId(taskListId)
        .name("Work")
        .createdAt(Date.valueOf("2023-08-20"))
        .createdBy(user)
        .build();

    var taskLists = List.of(taskList);
    user.setTaskLists(taskLists);

    var taskId = UUID.randomUUID().toString();
    var task = TaskDb.builder()
        .taskId(taskId)
        .title("Complete GraphQL project")
        .description("A mini project to demonstrate how to use GraphQL with Spring boot.")
        .dueDate(Date.valueOf("2023-08-26"))
        .createdAt(Date.valueOf("2023-08-20"))
        .modifiedAt(Date.valueOf("2023-08-20"))
        .build();

    Mockito.when(mockUserService.getUserById(userId))
        .thenReturn(user);

    Mockito.when(mockTaskListService.getTaskListByTaskListId(userId, taskListId))
        .thenReturn(taskList);

    var savedTask = TaskDb.builder()
        .taskId(taskId)
        .title("Complete GraphQL project")
        .description("A mini project to demonstrate how to use GraphQL with Spring boot.")
        .createdBy(user)
        .dueDate(Date.valueOf("2023-08-26"))
        .createdAt(Date.valueOf("2023-08-20"))
        .modifiedAt(Date.valueOf("2023-08-20"))
        .taskList(taskList)
        .build();

    Mockito.when(mockTaskRepository.save(task))
        .thenReturn(savedTask);

    var actual = mockTaskService.createTask(userId, task, taskListId);

    Assertions.assertEquals(savedTask.getTaskId(), actual.getTaskId());
    Assertions.assertEquals(savedTask.getCreatedBy().getUserId(), actual.getCreatedBy().getUserId());
    Assertions.assertEquals(savedTask.getTaskList().getTaskListId(), actual.getTaskList().getTaskListId());
  }

  @Test
  void givenInvalidUserIdAndValidTaskListIdAndTaskData_thenCreateTaskIsCalled_thenThrowException() {
    var userId = UUID.randomUUID().toString();
    var taskListId = UUID.randomUUID().toString();
    var task = TaskDb.builder().build();

    Mockito.when(mockUserService.getUserById(userId))
        .thenThrow(UserNotFoundException.class);

    Assertions.assertThrows(UserNotFoundException.class,
        () -> mockTaskService.createTask(userId, task, taskListId));
  }

  @Test
  void givenValidUserIdAndInvalidTaskListIdAndTaskData_thenCreateTaskIsCalled_thenThrowException() {
    var userId = UUID.randomUUID().toString();
    var taskListId = UUID.randomUUID().toString();

    var user = UserDb.builder()
        .userId(userId)
        .name("Akshay")
        .username("akshay")
        .password("test-password-1")
        .createdAt(Date.valueOf("2023-08-19"))
        .modifiedAt(Date.valueOf("2023-08-19"))
        .build();

    var task = TaskDb.builder().build();

    Mockito.when(mockUserService.getUserById(userId))
        .thenReturn(user);

    Mockito.when(mockTaskListService.getTaskListByTaskListId(userId, taskListId))
        .thenThrow(TaskListNotFoundException.class);

    Assertions.assertThrows(TaskListNotFoundException.class,
        () -> mockTaskService.createTask(userId, task, taskListId));
  }
}