package com.emendes.offer.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductResponse(
    Long id,
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    String name,
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    String description,
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    BigDecimal price
) {
}
