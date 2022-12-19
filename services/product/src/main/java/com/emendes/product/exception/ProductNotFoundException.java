package com.emendes.product.exception;

import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends ResourceNotFoundException {

  public ProductNotFoundException(String message) {
    super(message, HttpStatus.NOT_FOUND);
  }

  public ProductNotFoundException(String message, HttpStatus httpStatus) {
    super(message, httpStatus);
  }

}
