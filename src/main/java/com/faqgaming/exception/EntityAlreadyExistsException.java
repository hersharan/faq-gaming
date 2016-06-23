package com.faqgaming.exception;

import org.springframework.http.HttpStatus;

public class EntityAlreadyExistsException extends FaqGamingException {

  private static final long serialVersionUID = 2377456374909579193L;
  
  private static final String DEFAULT_MESSAGE = "Already exists !";

  private static final HttpStatus DEFAULT_HTTP_STATUS = HttpStatus.CONFLICT;

  public EntityAlreadyExistsException() {
    this(DEFAULT_HTTP_STATUS, DEFAULT_MESSAGE);
  }

  public EntityAlreadyExistsException(HttpStatus httpStatus, String message) {
    super(httpStatus, DEFAULT_HTTP_STATUS, message, DEFAULT_MESSAGE);
  }

  public EntityAlreadyExistsException(String message) {
    super(DEFAULT_HTTP_STATUS, message);
  }

}
