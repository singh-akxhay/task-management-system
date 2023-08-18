package com.singhakxhay.taskmanagement.presentation.models.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskInput {
  private String title;
  private String description;
  private String dueDate;
}
