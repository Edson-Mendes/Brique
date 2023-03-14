package com.emendes.product.dto.response;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@SuperBuilder
@ToString
@EqualsAndHashCode
public class ProblemDetail {

  private String title;
  private String detail;
  private int status;
  private LocalDateTime timestamp;
  private String path;

}
