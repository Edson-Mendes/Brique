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
public class ErrorDetails {

  private String title;
  private String message;
  private int status;
  private LocalDateTime timestamp;
  private String path;

}
