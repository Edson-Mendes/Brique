package com.emendes.gateway.security;

import com.emendes.gateway.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.core.publisher.Mono;

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
  public ReactiveUserDetailsService userDetailsService() {
//    return username -> {
//      log.info("Searching for user with email: {}", username);
//      return userRepository.findByEmail(username);
//    };
    return username -> {
      log.info("Searching for user with email: {}", username);
      if (username.equals("edson@email.com")) {
        User user = User.builder()
            .username("edson@email.com")
            .password("{bcrypt}$2a$10$tzCoRr5b3qcMZHNaO.m8Rez28dzZhJNJE/PwkzOAUQEYd9Uworv6q")
            .authorities("ROLE_USER")
            .build();

        return Mono.just(user);
      }
      return Mono.empty();
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
