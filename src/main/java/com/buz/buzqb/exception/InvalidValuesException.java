package com.buz.buzqb.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidValuesException extends RuntimeException implements ExceptionDetails {

  private Map<String, String> messages;

  public InvalidValuesException() {
    super();
    messages = new HashMap<>();
  }

  public InvalidValuesException(String field, String message) {
    this();
    messages.put(field, message);
  }

  public void put(String field, String message) {
    messages.put(field, message);
  }

  public Map<String, String> getMessages() {
    return messages;
  }

  @Override
  public List<String> getAddtionalData() {
    return ExceptionDetails.getMap(getMessages());
  }
}
