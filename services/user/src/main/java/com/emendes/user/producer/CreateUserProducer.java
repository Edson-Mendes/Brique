package com.emendes.user.producer;

import com.emendes.user.dto.event.CreateUserEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

import static com.emendes.user.config.BeanConfig.CREATE_USER_TOPIC;

/**
 * Producer responsável por enviar as informações de usuário criados para um tópico Kafka.
 */
@RequiredArgsConstructor
@Component
@Slf4j
public class CreateUserProducer {

  private final KafkaTemplate<String, CreateUserEvent> kafkaTemplate;

  /**
   * Envia createUserEvent para o tópico 'create-user-event'.
   *
   * @param createUserEvent contendo as informações do usuário cadastrado.
   */
  public void sendMessage(CreateUserEvent createUserEvent) {
    // TODO: Validar createUserEvent
    CompletableFuture<SendResult<String, CreateUserEvent>> future = kafkaTemplate
        .send(CREATE_USER_TOPIC, createUserEvent.username(), createUserEvent);

    future.thenAccept(result -> log.info("Produced event to topic {}: key = {}, value = {}", CREATE_USER_TOPIC, createUserEvent.username(), createUserEvent));
  }

}
