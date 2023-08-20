package com.singhakxhay.taskmanagement.service.tasklist;

import com.singhakxhay.taskmanagement.data.models.TaskListDb;
import com.singhakxhay.taskmanagement.data.models.UserDb;
import com.singhakxhay.taskmanagement.data.repository.TaskListRepository;
import com.singhakxhay.taskmanagement.exception.tasklist.TaskListAlreadyExistsException;
import com.singhakxhay.taskmanagement.exception.tasklist.TaskListNotFoundException;
import com.singhakxhay.taskmanagement.exception.user.UserNotFoundException;
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
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class TaskListServiceImplTest {
  @InjectMocks
  private TaskListServiceImpl mockTaskListService;
  @Mock
  private TaskListRepository mockTaskListRepository;
  @Mock
  private UserService mockUserService;

  @Test
  void giveValidUserId_thenGetTaskListsByUserIdIsCalled_thenReturnTaskLists() {
    var userId = UUID.randomUUID().toString();

    var user = UserDb.builder()
        .userId(userId)
        .name("Akshay")
        .username("akshay")
        .password("test-password-1")
        .createdAt(Date.valueOf("2023-08-19"))
        .modifiedAt(Date.valueOf("2023-08-19"))
        .build();

    var taskList1 = TaskListDb.builder()
        .taskListId(UUID.randomUUID().toString())
        .name("Work")
        .createdAt(Date.valueOf("2023-08-20"))
        .createdBy(user)
        .build();

    var taskList2 = TaskListDb.builder()
        .taskListId(UUID.randomUUID().toString())
        .name("Home")
        .createdAt(Date.valueOf("2023-08-20"))
        .createdBy(user)
        .build();

    var taskLists = List.of(taskList1, taskList2);
    user.setTaskLists(taskLists);

    Mockito.when(mockUserService.getUserById(userId))
        .thenReturn(user);

    var actual = mockTaskListService.getTaskListsByUserId(userId);

    Assertions.assertEquals(taskLists.size(), actual.size());

    for (int i = 0; i < actual.size(); i++) {
      Assertions.assertEquals(taskLists.get(i).getTaskListId(), actual.get(i).getTaskListId());
    }
  }

  @Test
  void giveInvalidUserId_thenGetTaskListsByUserIdIsCalled_thenThrowException() {
    var userId = UUID.randomUUID().toString();

    Mockito.when(mockUserService.getUserById(userId))
        .thenThrow(UserNotFoundException.class);

    Assertions.assertThrows(UserNotFoundException.class, () -> mockTaskListService.getTaskListsByUserId(userId));
  }

  @Test
  void givenValidUserIdAndValidTaskListData_thenCreateTaskListIsCalled_thenReturnTaskList() {
    var userId = UUID.randomUUID().toString();

    var user = UserDb.builder()
        .userId(userId)
        .name("Akshay")
        .username("akshay")
        .password("test-password-1")
        .createdAt(Date.valueOf("2023-08-19"))
        .modifiedAt(Date.valueOf("2023-08-19"))
        .build();

    Mockito.when(mockUserService.getUserById(userId))
        .thenReturn(user);

    var taskList = TaskListDb.builder()
        .name("Work")
        .createdAt(Date.valueOf("2023-08-20"))
        .build();

    Mockito.when(mockTaskListRepository.existsByName(taskList.getName()))
        .thenReturn(Boolean.FALSE);

    var taskListId = UUID.randomUUID().toString();
    var savedTaskList = TaskListDb.builder()
        .taskListId(taskListId)
        .name("Work")
        .createdAt(Date.valueOf("2023-08-20"))
        .createdBy(user)
        .build();

    Mockito.when(mockTaskListRepository.save(taskList))
        .thenReturn(savedTaskList);

    var actual = mockTaskListService.createTaskList(userId, taskList);

    Assertions.assertEquals(savedTaskList.getTaskListId(), actual.getTaskListId());
    Assertions.assertEquals(savedTaskList.getCreatedBy().getUserId(), actual.getCreatedBy().getUserId());
  }

  @Test
  void givenInvalidUserIdAndValidTaskListData_thenCreateTaskListIsCalled_thenThrowException() {
    var userId = UUID.randomUUID().toString();

    Mockito.when(mockUserService.getUserById(userId))
        .thenThrow(UserNotFoundException.class);

    var taskList = TaskListDb.builder().build();

    Assertions.assertThrows(UserNotFoundException.class, () -> mockTaskListService.createTaskList(userId, taskList));
  }

  @Test
  void givenValidUserIdAndInvalidTaskListData_thenCreateTaskListIsCalled_thenThrowException() {
    var userId = UUID.randomUUID().toString();

    var user = UserDb.builder().build();
    Mockito.when(mockUserService.getUserById(userId))
        .thenReturn(user);

    var taskList = TaskListDb.builder().build();
    Mockito.when(mockTaskListRepository.existsByName(taskList.getName()))
        .thenReturn(Boolean.TRUE);

    Assertions.assertThrows(TaskListAlreadyExistsException.class, () -> mockTaskListService.createTaskList(userId, taskList));
  }

  @Test
  void givenValidUserIdAndValidTaskListId_thenCreateTaskListIsCalled_thenReturnTaskList() {
    var userId = UUID.randomUUID().toString();

    var user = UserDb.builder()
        .userId(userId)
        .name("Akshay")
        .username("akshay")
        .password("test-password-1")
        .createdAt(Date.valueOf("2023-08-19"))
        .modifiedAt(Date.valueOf("2023-08-19"))
        .build();

    var taskListId = UUID.randomUUID().toString();

    var taskList = TaskListDb.builder()
        .taskListId(taskListId)
        .name("Work")
        .createdAt(Date.valueOf("2023-08-20"))
        .createdBy(user)
        .build();

    Mockito.when(mockTaskListRepository.findById(taskListId))
        .thenReturn(Optional.of(taskList));

    var actual = mockTaskListService.getTaskListByTaskListId(userId, taskListId);
    Assertions.assertEquals(taskList.getCreatedBy().getUserId(), actual.getCreatedBy().getUserId());
  }

  @Test
  void givenInvalidTaskListId_thenCreateTaskListIsCalled_thenThrowException() {
    var userId = UUID.randomUUID().toString();

    var taskListId = UUID.randomUUID().toString();

    Mockito.when(mockTaskListRepository.findById(taskListId))
        .thenReturn(Optional.empty());

    Assertions.assertThrows(TaskListNotFoundException.class, () -> mockTaskListService.getTaskListByTaskListId(userId, taskListId));
  }

  @Test
  void givenInvalidUserId_thenCreateTaskListIsCalled_thenThrowException() {
    var userId = UUID.randomUUID().toString();

    var user = UserDb.builder()
        .userId(UUID.randomUUID().toString())
        .name("Akshay")
        .username("akshay")
        .password("test-password-1")
        .createdAt(Date.valueOf("2023-08-19"))
        .modifiedAt(Date.valueOf("2023-08-19"))
        .build();

    var taskListId = UUID.randomUUID().toString();

    var taskList = TaskListDb.builder()
        .taskListId(taskListId)
        .name("Work")
        .createdAt(Date.valueOf("2023-08-20"))
        .createdBy(user)
        .build();

    Mockito.when(mockTaskListRepository.findById(taskListId))
        .thenReturn(Optional.of(taskList));

    Assertions.assertThrows(UnsupportedOperationException.class, () -> mockTaskListService.getTaskListByTaskListId(userId, taskListId));
  }
}