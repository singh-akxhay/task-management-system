package com.singhakxhay.taskmanagement.presentation.models.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {
  private String taskId;
  private String title;
  private String description;
  private Date createdAt;
  private TaskStatus taskStatus;
  private Date dueDate;
}

