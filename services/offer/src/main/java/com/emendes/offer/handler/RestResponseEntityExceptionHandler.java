package com.emendes.offer.handler;

import com.emendes.offer.dto.response.ProblemDetail;
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
  public ResponseEntity<ProblemDetail> handleResourceNotFoundException(
      InvalidOfferException exception, HttpServletRequest request) {

    ProblemDetail problemDetail = ProblemDetail.builder()
        .title("Invalid offer")
        .detail(exception.getMessage())
        .status(exception.getStatus().value())
        .timestamp(LocalDateTime.now())
        .path(request.getRequestURI())
        .build();

    return ResponseEntity.status(exception.getStatus()).body(problemDetail);
  }

  @ExceptionHandler(ConnectException.class)
  public ResponseEntity<ProblemDetail> handleConnectException(
      ConnectException exception, HttpServletRequest request) {
    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    log.error(exception.getMessage(), exception);

    ProblemDetail problemDetail = ProblemDetail.builder()
        .title("Something went wrong")
        .detail("Something went wrong, please try again later")
        .status(status.value())
        .timestamp(LocalDateTime.now())
        .path(request.getRequestURI())
        .build();

    return ResponseEntity.status(status).body(problemDetail);
  }

}
