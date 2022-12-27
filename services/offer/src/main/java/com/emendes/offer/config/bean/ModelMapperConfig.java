package com.emendes.offer.config.bean;

import com.emendes.offer.dto.request.OfferRequest;
import com.emendes.offer.model.entity.Offer;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

  @Bean
  public ModelMapper modelMapper() {
    ModelMapper mapper = new ModelMapper();

    PropertyMap<OfferRequest, Offer> offerMap = new PropertyMap<>() {
      protected void configure() {
        map().setId(null);
      }
    };
    mapper.addMappings(offerMap);

    return mapper;
  }

}
