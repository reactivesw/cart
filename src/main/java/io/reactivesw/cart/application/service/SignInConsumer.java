package io.reactivesw.cart.application.service;

import io.reactivesw.cart.application.model.event.SignInEvent;
import io.reactivesw.cart.infrastructure.configuration.EventConfig;
import io.reactivesw.message.client.consumer.Consumer;
import io.reactivesw.message.client.core.DefaultConsumerFactory;
import io.reactivesw.message.client.core.Message;
import io.reactivesw.message.client.utils.serializer.JsonDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Sign in event consumer.
 */
@Service
public class SignInConsumer {
  /**
   * logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(SignInConsumer.class);

  /**
   * message consumer.
   */
  private transient Consumer consumer;

  /**
   * Cart Merger.
   */
  @Autowired
  private transient CartMerger cartMerger;

  /**
   * json deserializer.
   */
  private transient JsonDeserializer<SignInEvent> jsonDeserializer;

  /**
   * default constructor.
   */
  @Autowired
  public SignInConsumer(EventConfig eventConfig) {
    consumer = DefaultConsumerFactory.createGoogleConsumer(eventConfig.getGoogleCloudProjectId(),
        eventConfig.getSigninSubscriber());
    jsonDeserializer = new JsonDeserializer(SignInEvent.class);
  }

  /**
   * executor.
   * Executes each 200 ms.
   */
  @Scheduled(fixedRate = 200)
  public void executor() {

    // Pull messages todo this should be configurable.
    List<Message> events = consumer.pullMessages(10);

    events.stream().forEach(
        message -> {

          SignInEvent event = jsonDeserializer.deserialize(message.getData().toString());
          LOG.info("Process event: {}.", message.getData().toString());

          if (event.getAnonymousId() != null) {
            // Only cart with anonymous id need to be processed.
            cartMerger.mergeCart(event.getCustomerId(), event.getAnonymousId());
            consumer.acknowledgeMessage(message.getExternalId());//for google we put ach
            LOG.debug("Processed message. messageId: {},  externalId: {}", message.getId(), message
                .getExternalId());
          }

        }
    );

  }


}
