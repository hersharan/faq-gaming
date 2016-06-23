package com.faqgaming.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.faqgaming.exception.EntityNotFoundException;
import com.faqgaming.exception.FaqGamingException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

  @ExceptionHandler(value = {Exception.class})
  protected ResponseEntity<RestResponse<?>> handleUnknownException(Exception ex, WebRequest request) {
    LOGGER.error(ex.getMessage(), ex);
    return RestUtils.errorResponse(FaqGamingException.DEFAULT_MESSAGE, FaqGamingException.DEFAULT_HTTP_STATUS);
  }

  @ExceptionHandler(value = {FaqGamingException.class})
  protected ResponseEntity<RestResponse<?>> handleInstadocException(FaqGamingException ex, WebRequest request) {
    LOGGER.error(ex.getMessage(), ex);
    return RestUtils.errorResponse(ex.getMessage(), ex.getHttpStatus());
  }

  @ExceptionHandler(value = {EntityNotFoundException.class})
  protected ResponseEntity<RestResponse<?>> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
    LOGGER.error(ex.getMessage(), ex);
    return RestUtils.errorResponse(ex.getMessage(), ex.getHttpStatus());
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    return new ResponseEntity<Object>(new RestResponse<>(Boolean.FALSE, convertConstraintViolation(ex), null),
        HttpStatus.BAD_REQUEST);
  }

  private String convertConstraintViolation(MethodArgumentNotValidException ex) {
    List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
    List<String> errorMessages = new ArrayList<String>();
    for (FieldError c : fieldErrors) {
      errorMessages.add(c.getField() + '-' + c.getDefaultMessage());
    }
    return errorMessages.toString();
  }

}
