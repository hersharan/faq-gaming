package com.faqgaming.exception;

import org.springframework.http.HttpStatus;

public class NotAuthorisedException extends FaqGamingException {

  private static final long serialVersionUID = 1820036124171588807L;

  private static final String DEFAULT_MESSAGE = "Not Authorized !";

  private static final HttpStatus DEFAULT_HTTP_STATUS = HttpStatus.UNAUTHORIZED;

  public NotAuthorisedException() {
    this(DEFAULT_HTTP_STATUS, DEFAULT_MESSAGE);
  }

  public NotAuthorisedException(HttpStatus httpStatus, String message) {
    super(httpStatus, DEFAULT_HTTP_STATUS, message, DEFAULT_MESSAGE);

  }

  public NotAuthorisedException(String message) {
    this(DEFAULT_HTTP_STATUS, message);
  }

}
