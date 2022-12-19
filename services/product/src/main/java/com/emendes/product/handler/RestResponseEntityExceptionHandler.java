package com.emendes.product.handler;

import com.emendes.product.dto.response.ErrorDetails;
import com.emendes.product.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorDetails> handleResourceNotFoundException(
      ResourceNotFoundException exception, HttpServletRequest request) {

    ErrorDetails errorDetails = ErrorDetails.builder()
        .error("Resource not found")
        .message(exception.getMessage())
        .status(exception.getHttpStatus().value())
        .timestamp(LocalDateTime.now())
        .path(request.getRequestURI())
        .build();

    return ResponseEntity.status(exception.getHttpStatus()).body(errorDetails);

  }

}
