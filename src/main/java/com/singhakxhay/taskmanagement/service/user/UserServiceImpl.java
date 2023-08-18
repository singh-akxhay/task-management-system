package com.singhakxhay.taskmanagement.service.user;

import com.singhakxhay.taskmanagement.data.models.UserDb;
import com.singhakxhay.taskmanagement.data.repository.UserRepository;
import com.singhakxhay.taskmanagement.exception.user.UserNotFoundException;
import com.singhakxhay.taskmanagement.exception.user.UsernameAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;

  @Override
  public UserDb createUser(UserDb user) {
    // Check if username already exists or not
    var usernameExists = userRepository.existsByUsername(user.getUsername());
    if (Boolean.TRUE.equals(usernameExists)) {
      throw new UsernameAlreadyExistsException("Username=%s already exists".formatted(user.getUsername()));
    }

    // Create new user
    return userRepository.save(user);
  }

  @Override
  public UserDb getUserById(String userId) {
    return userRepository.findById(userId)
        .orElseThrow(() -> new UserNotFoundException("User ID=%s not found".formatted(userId)));
  }

  @Override
  public UserDb getUserByUsername(String username) {
    return userRepository.findByUsername(username)
        .orElseThrow(() -> new UserNotFoundException("Username=%s not found".formatted(username)));
  }
}
