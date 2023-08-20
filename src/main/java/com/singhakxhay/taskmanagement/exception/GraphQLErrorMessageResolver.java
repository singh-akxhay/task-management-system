package com.singhakxhay.taskmanagement.exception;

import com.singhakxhay.taskmanagement.exception.tasklist.TaskListAlreadyExistsException;
import com.singhakxhay.taskmanagement.exception.tasklist.TaskListNotFoundException;
import com.singhakxhay.taskmanagement.exception.user.UserNotFoundException;
import com.singhakxhay.taskmanagement.exception.user.UsernameAlreadyExistsException;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;

@Component
public class GraphQLErrorMessageResolver extends DataFetcherExceptionResolverAdapter {
  @Override
  protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
    var errorType = CustomErrorType.INTERNAL_SERVER_ERROR;

    if (ex instanceof UserNotFoundException
        || ex instanceof TaskListNotFoundException) {
      errorType = CustomErrorType.NOT_FOUND;
    } else if (ex instanceof UsernameAlreadyExistsException
        || ex instanceof TaskListAlreadyExistsException) {
      errorType = CustomErrorType.CONFLICT;
    }

    return GraphqlErrorBuilder.newError(env)
        .errorType(errorType)
        .message(ex.getMessage())
        .build();
  }
}