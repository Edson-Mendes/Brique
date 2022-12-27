package com.emendes.offer.client.impl;

import com.emendes.offer.client.ProductClient;
import com.emendes.offer.client.response.ProductResponse;
import com.emendes.offer.exception.InvalidOfferException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Component
public class ProductClientImpl implements ProductClient {

  private final WebClient client;

  @Override
  public ProductResponse findProduct(Long id) {
    URI uri = URI.create("http://localhost:8880/api/products/" + id);

    Mono<ProductResponse> response = client.get().uri(uri)
        .accept(MediaType.APPLICATION_JSON).acceptCharset(StandardCharsets.UTF_8).retrieve()
//        .onStatus(HttpStatus.NOT_FOUND::equals, r -> r.bodyToMono(String.class).map(RuntimeException::new))
        .onStatus(HttpStatus.NOT_FOUND::equals, r -> {
          return Mono.error(new InvalidOfferException("The specified product was not found", HttpStatus.BAD_REQUEST));
        })
        .bodyToMono(ProductResponse.class);
    return response.block();
  }

}
