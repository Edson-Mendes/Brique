package com.emendes.gateway.repository;


import com.emendes.gateway.model.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;

/**
 * Interface repository com as abstrações para persistência do recurso User.
 */
public interface UserRepository extends ReactiveCrudRepository<User, Long> {

  /**
   * Busca UserDetails por username.
   *
   * @param username corresponde ao E-mail do User.
   * @return Mono of UserDetails.
   */
  Mono<UserDetails> findByUsername(String username);

}
