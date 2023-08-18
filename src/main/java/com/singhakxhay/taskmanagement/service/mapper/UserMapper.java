package com.singhakxhay.taskmanagement.service.mapper;

import com.singhakxhay.taskmanagement.data.models.UserDb;
import com.singhakxhay.taskmanagement.presentation.models.user.User;
import com.singhakxhay.taskmanagement.presentation.models.user.UserInput;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
  static UserMapper getInstance() {
    return UserMapperInstance.INSTANCE;
  }

  User mapToUser(UserDb user);

  UserDb mapToUserDb(UserInput userInput);

  final class UserMapperInstance {
    private static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    private UserMapperInstance() {

    }
  }
}
