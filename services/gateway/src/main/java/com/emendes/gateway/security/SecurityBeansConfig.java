package com.emendes.gateway.security;

import com.emendes.gateway.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Beans usados em conjunto com o security.
 */
@Slf4j
@Configuration
public class SecurityBeansConfig {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Bean
  public ReactiveUserDetailsService userDetailsService(UserRepository userRepository) {
    return username -> {
      log.info("Searching for user with email: {}", username);
      return userRepository.findByUsername(username);
    };
  }

  @Bean
  public ReactiveAuthenticationManager reactiveAuthenticationManager(
      ReactiveUserDetailsService userDetailsService,
      PasswordEncoder passwordEncoder) {
    var authenticationManager = new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService);
    authenticationManager.setPasswordEncoder(passwordEncoder);
    return authenticationManager;
  }

}
