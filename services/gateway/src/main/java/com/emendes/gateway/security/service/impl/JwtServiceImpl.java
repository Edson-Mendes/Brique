package com.emendes.gateway.security.service.impl;

import com.emendes.gateway.model.entity.User;
import com.emendes.gateway.security.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

/**
 * Implementação de {@link JwtService}
 */
@Slf4j
@Service
public class JwtServiceImpl implements JwtService {

  @Value("${brique.jwt.expiration}")
  private String expiration;

  @Value("${brique.jwt.secret}")
  private String secret;

  @Override
  public String generateJWT(User user) {
    return Jwts.builder()
        .setIssuer("Brique")
        .setSubject(user.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(expiration)))
        .signWith(getKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  @Override
  public boolean isTokenValid(String token) {
    try {
      extractAllClaims(token);
      return true;
    } catch (JwtException | IllegalArgumentException exception) {
      return false;
    }
  }

  @Override
  public String extractSubject(String token) {
    return extractAllClaims(token).getSubject();
  }

  /**
   * Gera uma Key com base no compo secret.
   */
  private Key getKey() {
    byte[] secretBytes = secret.getBytes();
    return Keys.hmacShaKeyFor(secretBytes);
  }

  /**
   * Extrai todas as claims contidas no token.
   * @param token JWT que contém as claims.
   * @return Claims contidas no token.
   */
  private Claims extractAllClaims(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(getKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

}
