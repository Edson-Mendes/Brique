package com.emendes.product.handler;

import com.emendes.product.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.*;
import org.springframework.lang.NonNull;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller Advice para tratamento de algumas exception que podem ocorrer na aplicação.
 */
@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      @NonNull MethodArgumentNotValidException ex, @NonNull HttpHeaders headers,
      @NonNull HttpStatusCode status, @NonNull WebRequest request) {
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    List<FieldError> fieldErrors = ex.getFieldErrors();
    String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining("; "));
    String messages = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining("; "));

    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, "Some fields are invalid");

    problemDetail.setType(URI.create("https://github.com/Edson-Mendes/brique/problem-detail#invalid-fields"));
    problemDetail.setTitle("Invalid field(s)");
    problemDetail.setProperty("fields", fields);
    problemDetail.setProperty("messages", messages);

    return ResponseEntity.status(httpStatus).body(problemDetail);
  }

  @Override
  protected ResponseEntity<Object> handleTypeMismatch(
      @NonNull TypeMismatchException ex,
      @NonNull HttpHeaders headers,
      @NonNull HttpStatusCode status,
      @NonNull WebRequest request) {

    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, ex.getMessage());

    problemDetail.setType(URI.create("https://github.com/Edson-Mendes/brique/problem-detail#type-mismatch"));
    problemDetail.setTitle("Type Mismatch");

    return ResponseEntity.status(httpStatus).body(problemDetail);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ProblemDetail> handleResourceNotFoundException(
      ResourceNotFoundException exception, HttpServletRequest request) {

    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, exception.getMessage());

    problemDetail.setType(URI.create("https://github.com/Edson-Mendes/brique/problem-detail#resource-not-found"));
    problemDetail.setTitle("Resource not found");

    return ResponseEntity.status(exception.getHttpStatus()).body(problemDetail);

  }

}
