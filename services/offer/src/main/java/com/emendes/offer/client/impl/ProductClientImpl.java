package com.emendes.offer.client.impl;

import com.emendes.offer.client.ProductClient;
import com.emendes.offer.dto.response.ProductResponse;
import com.emendes.offer.exception.InvalidOfferException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
    URI uri = URI.create("http://localhost:8580/api/products/" + id);

    Mono<ProductResponse> response = client.get().uri(uri)
        .accept(MediaType.APPLICATION_JSON).acceptCharset(StandardCharsets.UTF_8).retrieve()
        .onStatus(HttpStatusCode::is4xxClientError,
            r -> Mono.error(new InvalidOfferException("The specified product does not exist", HttpStatus.BAD_REQUEST)))
        .bodyToMono(ProductResponse.class);
    return response.block();
  }

}
