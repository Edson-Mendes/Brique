package com.emendes.gateway.service;

import com.emendes.gateway.dto.request.AuthenticationRequest;
import com.emendes.gateway.dto.response.AuthenticationResponse;
import reactor.core.publisher.Mono;

/**
 * Interface service com as abstrações de autenticação de User.
 */
public interface AuthenticationService {

  /**
   * Autentica um User usando as credenciais recebidas em authenticationRequest.
   *
   * @param authenticationRequest credenciais do User (email e password)
   * @return AuthenticationResponse contendo token (JWT a ser enviado em requisições que requerem autenticação),
   * type (tipo do token, nessa aplicação apenas Bearer)
   */
  Mono<AuthenticationResponse> authenticate(AuthenticationRequest authenticationRequest);

}
