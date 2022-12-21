package com.emendes.offer.mapper;

import com.emendes.offer.dto.request.OfferRequest;
import com.emendes.offer.dto.response.OfferResponse;
import com.emendes.offer.model.entity.Offer;

public interface OfferMapper {

  Offer toOffer(OfferRequest offerRequest);

  OfferResponse toOfferResponse(Offer offer);
}
