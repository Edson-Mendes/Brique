package com.emendes.gateway.repository;


import com.emendes.gateway.model.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * Interface repository com as abstrações para persistência do recurso User.
 */
public interface UserRepository extends ReactiveCrudRepository<User, Long> {
}
