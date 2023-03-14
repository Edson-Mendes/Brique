package com.emendes.offer.service.impl;

import com.emendes.offer.client.ProductClient;
import com.emendes.offer.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

  private final ProductClient productClient;

  @Override
  public void verifyAvailability(Long id) {
    productClient.findProduct(id);
  }

}
