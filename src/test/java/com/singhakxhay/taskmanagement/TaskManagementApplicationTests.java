package com.singhakxhay.taskmanagement;

import com.singhakxhay.taskmanagement.data.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TaskManagementApplicationTests {
  @Autowired
  private UserRepository userRepository;

  @Test
  void contextLoads() {
    Assertions.assertNotNull(userRepository);
  }

}
