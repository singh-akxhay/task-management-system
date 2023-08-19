package com.singhakxhay.taskmanagement.exception.tasklist;

public class TaskListAlreadyExistsException extends RuntimeException {
  public TaskListAlreadyExistsException(String message) {
    super(message);
  }
}
