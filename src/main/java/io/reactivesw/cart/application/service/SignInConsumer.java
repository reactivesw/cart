package io.reactivesw.cart.application.service;

import io.reactivesw.cart.application.model.event.SignInEvent;
import io.reactivesw.cart.application.service.update.AddLineItemService;
import io.reactivesw.cart.domain.model.Cart;
import io.reactivesw.cart.domain.model.LineItem;
import io.reactivesw.cart.domain.service.CartService;
import io.reactivesw.cart.infrastructure.configuration.EventConfig;
import io.reactivesw.cart.infrastructure.enums.CartStatus;
import io.reactivesw.cart.infrastructure.repository.CartRepository;
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
   * add line item service.
   */
  @Autowired
  private transient AddLineItemService addLineItemService;

  /**
   * cart service.
   */
  @Autowired
  private transient CartService cartService;

  /**
   * cart repository.
   */
  @Autowired
  private transient CartRepository cartRepository;

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

          mergeCart(event.getCustomerId(), event.getAnonymousId());
          consumer.acknowledgeMessage(message.getExternalId());//for google we put ach
          LOG.debug("Processed message. messageId: {},  externalId: {}", message.getId(), message
              .getExternalId());
        }
    );

  }

  /**
   * merge cart.
   *
   * @param customerId  String
   * @param anonymousId String.
   */
  private void mergeCart(String customerId, String anonymousId) {

    Cart customerCart = cartService.getActiveCartByCustomerId(customerId);

    Cart anonymousCart = cartRepository.findOneByAnonymousId(anonymousId);
    if (anonymousCart == null) {
      LOG.error("Merge cart failed for anonymous cart not exist. customerId: {}, anonymousId: "
          + "{}.", customerId, anonymousId);

    } else if (anonymousCart.getCartStatus().equals(CartStatus.Merged)) {
      LOG.debug("Cart already merged. customerId: {}. anonymousId: {}.", customerId, anonymousId);

    } else {
      mergeCart(customerCart, anonymousCart);
    }
  }

  /**
   * merge this cart.
   *
   * @param customerCart  Cart
   * @param anonymousCart Cart
   */
  private void mergeCart(Cart customerCart, Cart anonymousCart) {
    LOG.trace("Enter. customerCart: {}, anonymousCart: {}.", customerCart, anonymousCart);
    List<LineItem> lineItems = anonymousCart.getLineItems();
    lineItems.stream().forEach(
        lineItem -> addLineItemService.mergeLineItem(customerCart, lineItem)
    );

    // Save cart after merged.
    Cart mergedCart = cartRepository.save(customerCart);
    //update anonymous cart's status.
    anonymousCart.setCartStatus(CartStatus.Merged);
    cartRepository.save(anonymousCart);
    LOG.trace("Exit. mergedCart: {}.", mergedCart);
  }

}
