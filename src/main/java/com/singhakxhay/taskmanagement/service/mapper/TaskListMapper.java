package com.singhakxhay.taskmanagement.service.mapper;

import com.singhakxhay.taskmanagement.data.models.TaskListDb;
import com.singhakxhay.taskmanagement.presentation.models.tasklist.TaskList;
import com.singhakxhay.taskmanagement.presentation.models.tasklist.TaskListInput;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TaskListMapper {
  static TaskListMapper getInstance() {
    return TaskListMapper.TaskListMapperInstance.INSTANCE;
  }

  TaskList mapToTaskList(TaskListDb taskListDb);

  List<TaskList> mapToTaskLists(List<TaskListDb> taskDbList);

  TaskListDb mapToTaskListDb(TaskListInput taskListInput);

  final class TaskListMapperInstance {
    private static final TaskListMapper INSTANCE = Mappers.getMapper(TaskListMapper.class);

    private TaskListMapperInstance() {

    }
  }
}
