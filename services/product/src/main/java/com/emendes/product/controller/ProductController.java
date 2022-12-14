package com.emendes.product.controller;

import com.emendes.product.dto.response.ProductResponse;
import com.emendes.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {

  private final ProductService productService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Page<ProductResponse> fetchAll(@PageableDefault Pageable pageable) {
    return productService.fetchAll(pageable);
  }

}
