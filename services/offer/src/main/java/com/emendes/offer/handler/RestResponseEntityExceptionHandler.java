package com.emendes.offer.handler;

import com.emendes.offer.dto.response.ErrorDetails;
import com.emendes.offer.exception.InvalidOfferException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.net.ConnectException;
import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(InvalidOfferException.class)
  public ResponseEntity<ErrorDetails> handleResourceNotFoundException(
      InvalidOfferException exception, HttpServletRequest request) {

    ErrorDetails errorDetails = ErrorDetails.builder()
        .title("Invalid offer")
        .message(exception.getMessage())
        .status(exception.getStatus().value())
        .timestamp(LocalDateTime.now())
        .path(request.getRequestURI())
        .build();

    return ResponseEntity.status(exception.getStatus()).body(errorDetails);
  }

  @ExceptionHandler(ConnectException.class)
  public ResponseEntity<ErrorDetails> handleConnectException(
      ConnectException exception, HttpServletRequest request) {
    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    log.error(exception.getMessage(), exception);

    ErrorDetails errorDetails = ErrorDetails.builder()
        .title("Something went wrong")
        .message("Something went wrong, please try again later")
        .status(status.value())
        .timestamp(LocalDateTime.now())
        .path(request.getRequestURI())
        .build();

    return ResponseEntity.status(status).body(errorDetails);
  }

}
