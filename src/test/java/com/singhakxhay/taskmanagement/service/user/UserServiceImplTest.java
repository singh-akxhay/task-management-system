package com.singhakxhay.taskmanagement.service.user;

import com.singhakxhay.taskmanagement.data.models.UserDb;
import com.singhakxhay.taskmanagement.data.repository.UserRepository;
import com.singhakxhay.taskmanagement.exception.user.UserNotFoundException;
import com.singhakxhay.taskmanagement.exception.user.UsernameAlreadyExistsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
  @InjectMocks
  private UserServiceImpl mockUserService;

  @Mock
  private UserRepository mockUserRepository;

  @Test
  void givenValidUserData_whenCreateUserIsCalled_thenReturnCreatedUser() {
    var user = UserDb.builder()
        .userId(UUID.randomUUID().toString())
        .name("Akshay")
        .username("akshay")
        .password("test-password-1")
        .createdAt(Date.valueOf("2023-08-19"))
        .modifiedAt(Date.valueOf("2023-08-19"))
        .build();

    Mockito.when(mockUserRepository.existsByUsername(ArgumentMatchers.anyString()))
        .thenReturn(Boolean.FALSE);

    Mockito.when(mockUserRepository.save(user))
        .thenReturn(user);

    var actual = mockUserService.createUser(user);

    Assertions.assertEquals(user.getUserId(), actual.getUserId());
  }

  @Test
  void givenInvalidUserData_whenCreateUserIsCalled_thenThrowException() {
    var username = "akshay";

    var user = UserDb.builder()
        .userId(UUID.randomUUID().toString())
        .name("Akshay")
        .username("akshay")
        .password("test-password-1")
        .createdAt(Date.valueOf("2023-08-19"))
        .modifiedAt(Date.valueOf("2023-08-19"))
        .build();

    Mockito.when(mockUserRepository.existsByUsername(username))
        .thenReturn(Boolean.TRUE);

    Assertions.assertThrows(UsernameAlreadyExistsException.class, () -> mockUserService.createUser(user));
  }

  @Test
  void givenValidUserId_thenGetUserByIdIsCalled_thenReturnUser() {
    var userId = UUID.randomUUID().toString();

    var user = UserDb.builder()
        .userId(userId)
        .name("Akshay")
        .username("akshay")
        .password("test-password-1")
        .createdAt(Date.valueOf("2023-08-19"))
        .modifiedAt(Date.valueOf("2023-08-19"))
        .build();

    Mockito.when(mockUserRepository.findById(userId))
        .thenReturn(Optional.of(user));

    var actual = mockUserService.getUserById(userId);

    Assertions.assertEquals(user.getUserId(), actual.getUserId());
  }

  @Test
  void givenInvalidUserId_thenGetUserByIdIsCalled_thenThrowException() {
    var userId = UUID.randomUUID().toString();

    Mockito.when(mockUserRepository.findById(userId))
        .thenReturn(Optional.empty());

    Assertions.assertThrows(UserNotFoundException.class, () -> mockUserService.getUserById(userId));
  }

  @Test
  void givenValidUsername_thenGetUserByIdIsCalled_thenReturnUser() {
    var username = "akshay";

    var user = UserDb.builder()
        .userId(UUID.randomUUID().toString())
        .name("Akshay")
        .username(username)
        .password("test-password-1")
        .createdAt(Date.valueOf("2023-08-19"))
        .modifiedAt(Date.valueOf("2023-08-19"))
        .build();

    Mockito.when(mockUserRepository.findByUsername(username))
        .thenReturn(Optional.of(user));

    var actual = mockUserService.getUserByUsername(username);

    Assertions.assertEquals(user.getUserId(), actual.getUserId());
  }

  @Test
  void givenInvalidUsername_thenGetUserByIdIsCalled_thenThrowException() {
    var username = "akshay";

    Mockito.when(mockUserRepository.findByUsername(username))
        .thenReturn(Optional.empty());

    Assertions.assertThrows(UserNotFoundException.class, () -> mockUserService.getUserByUsername(username));
  }
}