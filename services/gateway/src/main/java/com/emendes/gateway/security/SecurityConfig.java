package com.emendes.gateway.security;

import com.emendes.gateway.security.filter.JWTAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * Configurações de segurança.
 */
@RequiredArgsConstructor
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

  private final JWTAuthenticationFilter jwtAuthenticationFilter;

  private static final String[] EUREKA_WHITELIST = {"/eureka/web", "/eureka/**", "/favicon.ico"};
  private static final String[] POST_WHITELIST = {"/api/auth"};

  @Bean
  public SecurityWebFilterChain securityWebFilterChain(
      ServerHttpSecurity http, ReactiveAuthenticationManager authenticationManager) {
    http.csrf().disable()
        .httpBasic(Customizer.withDefaults());
//        .formLogin().disable();

    http.authenticationManager(authenticationManager)
        .addFilterBefore(jwtAuthenticationFilter, SecurityWebFiltersOrder.HTTP_BASIC);

    http.authorizeExchange()
        .pathMatchers(HttpMethod.GET, EUREKA_WHITELIST).permitAll()
        .pathMatchers(HttpMethod.POST, POST_WHITELIST).permitAll()
        .anyExchange().authenticated();

    return http.build();
  }

}
