package com.singhakxhay.taskmanagement.presentation.models.user;

import com.singhakxhay.taskmanagement.presentation.models.tasklist.TaskList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
  private String userId;
  private String name;
  private String username;
  private String createdAt;
  private List<TaskList> taskLists;
}
