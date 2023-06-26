package com.emendes.gateway.security.service;

import com.emendes.gateway.model.entity.User;

/**
 * Interface component com as abstrações para manipulação de JWT.
 */
public interface JwtService {

  /**
   * Gera um JWT a partir dos dados do User.
   *
   * @return jwt
   */
  String generateJWT(User user);

  /**
   * Verifica se o token informado é válido (não expirado, corresponde a assinatura).<br>
   * Se o token for null, o retorno será false.
   * @param token JWT
   * @return true se o token for válido, false caso contrário.
   */
  boolean isTokenValid(String token);

  /**
   * Extrai o subject do JWT.
   */
  String extractSubject(String token);

}
