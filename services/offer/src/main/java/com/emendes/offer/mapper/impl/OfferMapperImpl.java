package com.emendes.offer.mapper.impl;

import com.emendes.offer.dto.request.OfferRequest;
import com.emendes.offer.dto.response.OfferResponse;
import com.emendes.offer.dto.response.ProductResponse;
import com.emendes.offer.mapper.OfferMapper;
import com.emendes.offer.model.entity.Offer;
import org.springframework.stereotype.Component;

/**
 * Implementação de OfferMapper.
 */
@Component
public class OfferMapperImpl implements OfferMapper {

  @Override
  public Offer toOffer(OfferRequest offerRequest) {
    return Offer.builder()
        .value(offerRequest.value())
        .productId(offerRequest.productId())
        .build();
  }

  @Override
  public OfferResponse toOfferResponse(Offer offer, ProductResponse productResponse) {
    return OfferResponse.builder()
        .id(offer.getId())
        .value(offer.getValue())
        .status(offer.getStatus())
        .product(productResponse)
        .build();
  }

}
