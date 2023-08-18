package com.singhakxhay.taskmanagement.presentation.controller;

import com.singhakxhay.taskmanagement.presentation.models.user.User;
import com.singhakxhay.taskmanagement.presentation.models.user.UserInput;
import com.singhakxhay.taskmanagement.service.mapper.UserMapper;
import com.singhakxhay.taskmanagement.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserQueryResolver {
  private final UserService userService;

  @QueryMapping
  private User userById(@Argument String userId) {
    var userDb = userService.getUserById(userId);
    return UserMapper.getInstance().mapToUser(userDb);
  }

  @QueryMapping
  private User userByUsername(@Argument String username) {
    var userDb = userService.getUserByUsername(username);
    return UserMapper.getInstance().mapToUser(userDb);
  }

  @MutationMapping
  private User createUser(@Argument UserInput userInput) {
    var userDb = userService.createUser(UserMapper.getInstance().mapToUserDb(userInput));
    return UserMapper.getInstance().mapToUser(userDb);
  }
}

