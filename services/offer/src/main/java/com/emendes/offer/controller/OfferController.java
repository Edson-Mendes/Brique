package com.emendes.offer.controller;

import com.emendes.offer.dto.request.OfferRequest;
import com.emendes.offer.dto.response.OfferResponse;
import com.emendes.offer.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/offers")
public class OfferController {

  private final OfferService offerService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public OfferResponse makeOffer(@RequestBody @Valid OfferRequest offerRequest) {
    return offerService.save(offerRequest);
  }

}
