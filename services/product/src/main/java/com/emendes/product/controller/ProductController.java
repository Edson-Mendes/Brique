package com.emendes.product.controller;

import com.emendes.product.dto.request.ProductRequest;
import com.emendes.product.dto.response.ProductResponse;
import com.emendes.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ProductResponse save(@RequestBody @Valid ProductRequest productRequest) {
    return productService.save(productRequest);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ProductResponse findById(@PathVariable(name = "id") Long id) {
    return productService.find(id);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  void deleteById(@PathVariable(name = "id") Long id) {
    productService.delete(id);
  }

}
