package com.singhakxhay.taskmanagement.data.repository;

import com.singhakxhay.taskmanagement.data.models.UserDb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserDb, String> {
  Optional<UserDb> findByUsername(String username);

  Boolean existsByUsername(String username);
}
