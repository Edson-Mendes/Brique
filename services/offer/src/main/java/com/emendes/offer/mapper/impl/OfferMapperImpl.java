package com.emendes.offer.mapper.impl;

import com.emendes.offer.dto.request.OfferRequest;
import com.emendes.offer.dto.response.OfferResponse;
import com.emendes.offer.mapper.OfferMapper;
import com.emendes.offer.model.entity.Offer;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OfferMapperImpl implements OfferMapper {

  private final ModelMapper mapper;

  @Override
  public Offer toOffer(OfferRequest offerRequest) {
    return mapper.map(offerRequest, Offer.class);
  }

  @Override
  public OfferResponse toOfferResponse(Offer offer) {
    return mapper.map(offer, OfferResponse.class);
  }

}
