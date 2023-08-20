package com.singhakxhay.taskmanagement.data.repository;

import com.singhakxhay.taskmanagement.data.models.UserDb;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UserRepositoryTest {
  @Autowired
  private EntityManager entityManager;

  @Autowired
  private UserRepository userRepository;

  @Test
  void injectedComponentsAreNotNull() {
    assertThat(entityManager).isNotNull();
    assertThat(userRepository).isNotNull();
  }

  @Test
  void saveUser() {
    var user = UserDb.builder()
        .name("Test User1")
        .username("testusername1")
        .password("Test password 1")
        .build();

    var actual = userRepository.save(user);

    assertEquals(user.getUserId(), actual.getUserId());
  }

  @Test
  void givenUserId_whenFindByIdIsCalled_thenReturnNullIfUserNotFound() {
    var userId = "use_id_1";
    var actual = userRepository.findById(userId);

    assertEquals(Optional.empty(), actual);
  }


  private UserDb saveUser_thenReturnUser() {
    var user = UserDb.builder()
        .name("Test User1")
        .username("testusername1")
        .password("Test password 1")
        .createdAt(Date.valueOf("2023-08-19"))
        .modifiedAt(Date.valueOf("2023-08-19"))
        .build();

    return userRepository.save(user);
  }

  @Test
  void givenUserId_whenFindByIdIsCalled_thenReturnUser() {
    var expected = saveUser_thenReturnUser();

    var actual = userRepository.findById(expected.getUserId());

    actual.ifPresent(value -> {
      assertEquals(expected.getUserId(), value.getUserId());
      assertEquals(expected.getName(), value.getName());
      assertEquals(expected.getUsername(), value.getUsername());
      assertEquals(expected.getPassword(), value.getPassword());
      assertEquals(expected.getCreatedAt(), value.getCreatedAt());
      assertEquals(expected.getModifiedAt(), value.getModifiedAt());
    });
  }
}