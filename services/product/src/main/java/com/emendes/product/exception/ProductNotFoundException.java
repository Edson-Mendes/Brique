package com.emendes.product.exception;

public class ProductNotFoundException extends ResourceNotFoundException {

  public ProductNotFoundException(String message) {
    super(message);
  }

}
