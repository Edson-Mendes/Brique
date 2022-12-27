package com.emendes.offer.handler;

import com.emendes.offer.dto.response.ErrorDetails;
import com.emendes.offer.exception.InvalidOfferException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

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

}
