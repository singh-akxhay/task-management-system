package com.singhakxhay.taskmanagement.presentation.models.tasklist;

import com.singhakxhay.taskmanagement.presentation.models.task.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskList {
  private String taskListId;
  private String name;
  private String createdAt;
  private List<Task> tasks;
}
