package com.emendes.user.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeanConfig {

  public static final String CREATE_USER_TOPIC = "create-user-event";

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  /**
   * Bean responsável por criar o tópico 'create-user-event' se não existir.
   */
  @Bean
  public NewTopic newTopic() {
    return TopicBuilder.name(CREATE_USER_TOPIC)
        .partitions(1)
        .replicas(1)
        .build();
  }

}
