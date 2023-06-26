package com.emendes.gateway.service.impl;

import com.emendes.gateway.dto.request.AuthenticationRequest;
import com.emendes.gateway.dto.response.AuthenticationResponse;
import com.emendes.gateway.model.entity.User;
import com.emendes.gateway.security.service.JwtService;
import com.emendes.gateway.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * Implementação de {@link AuthenticationService}.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

  private final ReactiveAuthenticationManager reactiveAuthenticationManager;
  private final JwtService jwtService;

  @Override
  public Mono<AuthenticationResponse> authenticate(AuthenticationRequest authRequest) {
    log.info("attempt to sign in user : {}", authRequest.email());

    return reactiveAuthenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(authRequest.email(), authRequest.password()))
        .map(authentication -> {
          User user = (User) authentication.getPrincipal();
          String token = jwtService.generateJWT(user);

          log.info("JWT generated successfully for user {}", user.getUsername());

          return AuthenticationResponse.builder()
              .token(token)
              .type("Bearer")
              .build();
        });
  }

}
