package com.singhakxhay.taskmanagement.service.user;

import com.singhakxhay.taskmanagement.data.models.UserDb;
import com.singhakxhay.taskmanagement.data.repository.UserRepository;
import com.singhakxhay.taskmanagement.exception.user.UserNotFoundException;
import com.singhakxhay.taskmanagement.exception.user.UsernameAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;

  @Override
  public UserDb createUser(UserDb user) {
    log.info("[createUser] - Started creating user for user = {}", user);

    // Check if username already exists or not
    var usernameExists = userRepository.existsByUsername(user.getUsername());
    if (Boolean.TRUE.equals(usernameExists)) {
      throw new UsernameAlreadyExistsException("Username=%s already exists".formatted(user.getUsername()));
    }

    // Create new user
    var save = userRepository.save(user);

    log.info("[createUser] - Ended creating user for userId = {}", save.getUserId());

    return save;
  }

  @Override
  public UserDb getUserById(String userId) {
    log.info("[getUserById] - Started fetching user by userId = {}", userId);

    var user = userRepository.findById(userId)
        .orElseThrow(() -> new UserNotFoundException("User ID=%s not found".formatted(userId)));

    log.info("[getUserById] - Ended fetching user by userId = {}", userId);

    return user;
  }

  @Override
  public UserDb getUserByUsername(String username) {
    log.info("[getUserByUsername] - Started fetching user by username = {}", username);

    var user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UserNotFoundException("Username=%s not found".formatted(username)));

    log.info("[getUserByUsername] - Ended fetching user by username = {}", username);

    return user;
  }
}
