package com.emendes.product.handler;

import com.emendes.product.dto.response.ErrorDetails;
import com.emendes.product.dto.response.ValidationErrorDetails;
import com.emendes.product.exception.ResourceNotFoundException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorDetails> handleResourceNotFoundException(
      ResourceNotFoundException exception, HttpServletRequest request) {

    ErrorDetails errorDetails = ErrorDetails.builder()
        .title("Resource not found")
        .message(exception.getMessage())
        .status(exception.getHttpStatus().value())
        .timestamp(LocalDateTime.now())
        .path(request.getRequestURI())
        .build();

    return ResponseEntity.status(exception.getHttpStatus()).body(errorDetails);

  }

  @Override
  protected ResponseEntity<Object> handleTypeMismatch(
      TypeMismatchException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
    ErrorDetails errorDetails = ErrorDetails.builder()
        .title("Type Mismatch")
        .message(exception.getMessage())
        .status(status.value())
        .timestamp(LocalDateTime.now())
        .path(((ServletWebRequest) request).getRequest().getRequestURI())
        .build();

    return ResponseEntity.status(status).body(errorDetails);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
    List<FieldError> fieldErrors = exception.getFieldErrors();
    String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining("; "));
    String errors = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining("; "));

    ValidationErrorDetails errorDetails = ValidationErrorDetails.builder()
        .title("Type Mismatch")
        .message("Invalid field(s)")
        .status(status.value())
        .timestamp(LocalDateTime.now())
        .path(((ServletWebRequest) request).getRequest().getRequestURI())
        .fields(fields)
        .errors(errors)
        .build();

    return ResponseEntity.status(status).body(errorDetails);
  }
}
