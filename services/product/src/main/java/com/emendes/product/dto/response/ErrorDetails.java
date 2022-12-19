package com.emendes.product.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
@EqualsAndHashCode
public class ErrorDetails {

  private String error;
  private String message;
  private int status;
  private LocalDateTime timestamp;
  private String path;

}
