package com.faqgaming.exception;

import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

public class FaqGamingException extends Exception {

  private static final long serialVersionUID = -6181876116040697174L;

  public static final HttpStatus DEFAULT_HTTP_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;

  public static final String DEFAULT_MESSAGE = "Something Bad Happened !";

  private HttpStatus httpStatus;

  private String errorMessage;

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

  public void setHttpStatus(HttpStatus httpStatus) {
    this.httpStatus = httpStatus;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public FaqGamingException(HttpStatus httpStatus, String errorMessage) {
    super();
    this.httpStatus = httpStatus == null ? DEFAULT_HTTP_STATUS : httpStatus;
    this.errorMessage = StringUtils.isEmpty(errorMessage) ? DEFAULT_MESSAGE : errorMessage;
  }

  public FaqGamingException(HttpStatus httpStatus, HttpStatus defaultStatus, String errorMessage,
      String defaultMessage) {
    super();
    this.httpStatus = httpStatus == null ? defaultStatus : httpStatus;
    this.errorMessage = StringUtils.isEmpty(errorMessage) ? defaultMessage : errorMessage;
  }

  public FaqGamingException(String errorMessage) {
    this(DEFAULT_HTTP_STATUS, errorMessage);
  }

  public FaqGamingException() {
    this(DEFAULT_HTTP_STATUS, DEFAULT_MESSAGE);
  }

}
