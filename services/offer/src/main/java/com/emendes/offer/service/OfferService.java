package com.emendes.offer.service;

import com.emendes.offer.dto.request.OfferRequest;
import com.emendes.offer.dto.response.OfferResponse;

public interface OfferService {

  OfferResponse save(OfferRequest offerRequest);

}
