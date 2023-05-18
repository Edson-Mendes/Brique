package com.emendes.offer.service.impl;

import com.emendes.offer.dto.request.OfferRequest;
import com.emendes.offer.dto.response.OfferResponse;
import com.emendes.offer.dto.response.ProductResponse;
import com.emendes.offer.mapper.OfferMapper;
import com.emendes.offer.model.entity.Offer;
import com.emendes.offer.repository.OfferRepository;
import com.emendes.offer.service.OfferService;
import com.emendes.offer.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Implementação de {@link OfferService}.
 */
@RequiredArgsConstructor
@Slf4j
@Transactional
@Service
public class OfferServiceImpl implements OfferService {

  private final OfferRepository offerRepository;
  private final ProductService productService;
  private final OfferMapper offerMapper;

  @Override
  public OfferResponse makeOffer(OfferRequest offerRequest) {
    ProductResponse productResponse = productService.findAvailableProductById(offerRequest.productId());

    Offer offer = offerMapper.toOffer(offerRequest);
    offer.setCreatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
    offer.setStatus("WAITING");

    offerRepository.save(offer);
    log.info("offer with id {} was saved", offer.getId());
    // TODO: Enviar uma notificação ao dono do produto informando que ele recebeu uma oferta pelo produto

    return offerMapper.toOfferResponse(offer, productResponse);
  }

}
