package com.emendes.product.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class ResourceNotFoundException extends RuntimeException {

  private final HttpStatus httpStatus;

  protected ResourceNotFoundException(String message, HttpStatus httpStatus) {
    super(message);
    this.httpStatus = httpStatus;
  }

}
