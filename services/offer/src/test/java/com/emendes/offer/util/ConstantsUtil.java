package com.emendes.offer.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class ConstantsUtil {

  private ConstantsUtil() {}

  public static final Pageable PAGEABLE_DEFAULT = PageRequest.of(0, 10);

}
