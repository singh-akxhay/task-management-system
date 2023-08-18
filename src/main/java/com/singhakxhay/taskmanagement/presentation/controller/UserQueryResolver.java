package com.singhakxhay.taskmanagement.presentation.controller;

import com.singhakxhay.taskmanagement.presentation.models.user.User;
import com.singhakxhay.taskmanagement.presentation.models.user.UserInput;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserQueryResolver {
  @QueryMapping
  private User userById(@Argument String userId) {
    return null;
  }

  @QueryMapping
  private User userByUsername(@Argument String username) {
    return null;
  }

  @MutationMapping
  private User createUser(@Argument UserInput userInput) {
    return null;
  }
}

