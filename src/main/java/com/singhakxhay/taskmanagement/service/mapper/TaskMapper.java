package com.singhakxhay.taskmanagement.service.mapper;

import com.singhakxhay.taskmanagement.data.models.TaskDb;
import com.singhakxhay.taskmanagement.presentation.models.task.Task;
import com.singhakxhay.taskmanagement.presentation.models.task.TaskInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.sql.Date;
import java.util.List;

@Mapper
public interface TaskMapper {
  static TaskMapper getInstance() {
    return TaskMapperInstance.INSTANCE;
  }

  @Named("parseDate")
  static Date parseDate(String date) {
    return Date.valueOf(date);
  }

  Task mapToTask(TaskDb taskDb);

  List<Task> mapToTaskList(List<TaskDb> taskDbList);

  @Mapping(target = "dueDate", source = "dueDate", qualifiedByName = "parseDate")
  TaskDb mapToTaskDb(TaskInput taskInput);

  final class TaskMapperInstance {
    private static final TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    private TaskMapperInstance() {
    }
  }
}
