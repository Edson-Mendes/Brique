package com.emendes.user.controller;

import com.emendes.user.dto.request.CreateUserRequest;
import com.emendes.user.dto.response.UserResponse;
import com.emendes.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Classe que recebe as requisições que tratam do recurso User.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  /**
   * Método que trata a requisição POST /api/users<br>
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UserResponse register(@RequestBody @Valid CreateUserRequest createUserRequest) {
    return userService.register(createUserRequest);
  }

}
