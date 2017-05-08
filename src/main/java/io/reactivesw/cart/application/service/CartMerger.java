package io.reactivesw.cart.application.service;

import io.reactivesw.cart.application.service.update.AddLineItemService;
import io.reactivesw.cart.domain.model.Cart;
import io.reactivesw.cart.domain.model.LineItem;
import io.reactivesw.cart.domain.service.CartService;
import io.reactivesw.cart.infrastructure.enums.CartStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Cart merger can be used to merge to cart.
 */
@Service
public class CartMerger {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(CartMerger.class);

  /**
   * Cart service.
   */
  @Autowired
  private transient CartService cartService;


  /**
   * Add line item service.
   */
  @Autowired
  private transient AddLineItemService addLineItemService;

  /**
   * Merge cart.
   *
   * @param customerId  String
   * @param anonymousId String.
   */
  public void mergeCart(String customerId, String anonymousId) {

    Cart customerCart = cartService.getActiveCartByCustomerId(customerId);

    Cart anonymousCart = cartService.getCartByAnonymousId(anonymousId);
    if (anonymousCart == null
        || anonymousCart.getLineItems() == null
        || anonymousCart.getLineItems().isEmpty()) {
      LOG.error("Merge cart failed for anonymous cart not exist. customerId: {}, anonymousId: "
          + "{}.", customerId, anonymousId);

    } else if (anonymousCart.getCartStatus().equals(CartStatus.Merged)) {
      LOG.debug("Cart already merged. customerId: {}. anonymousId: {}.", customerId, anonymousId);

    } else {
      mergeCart(customerCart, anonymousCart);
    }
  }

  /**
   * Merge this cart.
   *
   * @param customerCart  Cart
   * @param anonymousCart Cart
   */
  public void mergeCart(Cart customerCart, Cart anonymousCart) {
    LOG.trace("Enter. customerCart: {}, anonymousCart: {}.", customerCart, anonymousCart);
    List<LineItem> lineItems = anonymousCart.getLineItems();
    lineItems.stream().forEach(
        lineItem -> addLineItemService.mergeLineItem(customerCart, lineItem)
    );

    // Save cart after merged.
    Cart mergedCart = cartService.saveCart(customerCart);
    //update anonymous cart's status.
    anonymousCart.setCartStatus(CartStatus.Merged);
    cartService.saveCart(anonymousCart);
    LOG.trace("Exit. mergedCart: {}.", mergedCart);
  }
}
