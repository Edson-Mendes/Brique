package com.emendes.gateway.consumer;

import com.emendes.gateway.dto.event.CreateUserEvent;
import com.emendes.gateway.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Classe responsável por consumir mensagens do topic 'create-user-event'.
 */
@RequiredArgsConstructor
@Component
@Slf4j
public class CreateUserConsumer {

  private final UserService userService;

  public static final String CREATE_USER_TOPIC = "create-user-event";
  public static final String GATEWAY_CREATE_USER_GROUP = "gateway-create-user-group";

  /**
   * Responsável por capturar o value da mensagem vinda do topic 'create-user-event'.
   *
   * @param createUserEvent que contém as informações necessárias para autenticação de usuário.
   */
  @KafkaListener(topics = {CREATE_USER_TOPIC}, groupId = GATEWAY_CREATE_USER_GROUP)
  public void handleUserCreation(CreateUserEvent createUserEvent) {
    log.info("consuming message with username: {}", createUserEvent.username());
    userService.save(createUserEvent)
        .subscribe(u -> log.info("user with id : {} successful saved", u.getId()));
  }

}
