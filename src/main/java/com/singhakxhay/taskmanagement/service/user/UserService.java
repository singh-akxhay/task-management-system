package com.singhakxhay.taskmanagement.service.user;

import com.singhakxhay.taskmanagement.data.models.UserDb;

public interface UserService {
  UserDb createUser(UserDb user);

  UserDb getUserById(String userId);

  UserDb getUserByUsername(String username);
}
