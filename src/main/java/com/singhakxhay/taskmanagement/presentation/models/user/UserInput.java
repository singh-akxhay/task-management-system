package com.singhakxhay.taskmanagement.presentation.models.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInput {
  private String name;
  private String username;
  private String password;
}