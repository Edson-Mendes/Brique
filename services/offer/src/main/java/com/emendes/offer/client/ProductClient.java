package com.emendes.offer.client;

import com.emendes.offer.client.response.ProductResponse;

public interface ProductClient {

  ProductResponse findProduct(Long id);

}
