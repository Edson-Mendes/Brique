package com.emendes.offer.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InvalidOfferException extends RuntimeException {

  private final HttpStatus status;

  public InvalidOfferException(String message, HttpStatus status) {
    super(message);
    this.status = status;
  }

}
