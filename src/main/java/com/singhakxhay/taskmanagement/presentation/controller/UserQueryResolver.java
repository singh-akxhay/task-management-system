package com.singhakxhay.taskmanagement.presentation.controller;

import com.singhakxhay.taskmanagement.presentation.models.user.User;
import com.singhakxhay.taskmanagement.presentation.models.user.UserInput;
import com.singhakxhay.taskmanagement.service.mapper.UserMapper;
import com.singhakxhay.taskmanagement.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserQueryResolver {
  private final UserService userService;

  @QueryMapping
  private User userById(@Argument String userId) {
    log.info("[userById] - Started fetching user by userId = {}", userId);

    var userDb = userService.getUserById(userId);
    var user = UserMapper.getInstance()
        .mapToUser(userDb);

    log.info("[userById] - Ended fetching user by userId = {}", userId);

    return user;
  }

  @QueryMapping
  private User userByUsername(@Argument String username) {
    log.info("[userByUsername] - Started fetching user by username = {}", username);

    var userDb = userService.getUserByUsername(username);
    var user = UserMapper.getInstance().mapToUser(userDb);

    log.info("[userByUsername] - Ended fetching user by username = {}", username);

    return user;
  }

  @MutationMapping
  private User createUser(@Argument UserInput userInput) {
    log.info("[createUser] - Started creating user for userInput = {}", userInput);

    var userDb = userService.createUser(UserMapper.getInstance().mapToUserDb(userInput));
    var user = UserMapper.getInstance().mapToUser(userDb);

    log.info("[createUser] - Ended creating user for userInput = {}", userInput);

    return user;
  }
}

