package com.emendes.offer.client;

import com.emendes.offer.dto.response.ProductResponse;

public interface ProductClient {

  ProductResponse findProduct(Long id);

}
