package io.reactivesw.cart.infrastructure.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Event config.
 */
@Configuration
@Data
public class EventConfig {

  /**
   * Google cloud project id.
   */
  @Value("${io.reactivesw.message.google.project.id}")
  private String googleCloudProjectId;

  /**
   * Signin topic name.
   */
  @Value("${io.reactivesw.message.topic.signin.name}")
  private String signinTopicName;

  /**
   * Signin topic version.
   */
  @Value("${io.reactivesw.message.topic.signin.version}")
  private String signinTopicVersion;

  /**
   * Signin topic subscriber name.
   */
  @Value("${io.reactivesw.message.topic.signin.subscriber}")
  private String signinSubscriber;
}
