package com.singhakxhay.taskmanagement.exception;

import graphql.ErrorClassification;

public enum CustomErrorType implements ErrorClassification {
  NOT_FOUND, CONFLICT, UNAUTHORIZED, BAD_REQUEST, INTERNAL_SERVER_ERROR
}
