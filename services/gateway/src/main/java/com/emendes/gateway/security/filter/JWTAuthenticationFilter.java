package com.emendes.gateway.security.filter;

import com.emendes.gateway.security.service.JwtService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class JWTAuthenticationFilter implements WebFilter {

  private final JwtService jwtService;
  private final ReactiveUserDetailsService userDetailsService;

  @Override
  public @NotNull Mono<Void> filter(
      @NotNull ServerWebExchange exchange, @NotNull WebFilterChain chain) {
    String token = extractToken(exchange);

    if (token != null && jwtService.isTokenValid(token)) {
      String username = jwtService.extractSubject(token);

      return userDetailsService.findByUsername(username)
          .map(userDetails -> new UsernamePasswordAuthenticationToken(
              userDetails, null, userDetails.getAuthorities())
          )
          .flatMap(authentication -> chain
              .filter(exchange)
              .contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication)));
    }

    return chain.filter(exchange);
  }

  /**
   * Extrai o token do header Authorization da requisição.
   *
   * @param exchange Objeto que contém a requisição.
   * @return token caso exista, e null caso a requisição não contenha o header Authorization ou o
   * seu valor não comece com 'Bearer '.
   */
  private String extractToken(ServerWebExchange exchange) {
    String authorizationHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
    if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) return null;

    return authorizationHeader.substring(7);
  }

}
