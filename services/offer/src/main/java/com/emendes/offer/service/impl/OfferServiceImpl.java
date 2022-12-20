package com.emendes.offer.service.impl;

import com.emendes.offer.dto.request.OfferRequest;
import com.emendes.offer.dto.response.OfferResponse;
import com.emendes.offer.repository.OfferRepository;
import com.emendes.offer.service.OfferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Transactional
@Service
public class OfferServiceImpl implements OfferService {

  private final OfferRepository offerRepository;

  @Override
  public OfferResponse save(OfferRequest offerRequest) {
//  TODO: montar as regras para salvar um oferta.
    return null;
  }

}
