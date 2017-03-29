package io.reactivesw.cart.application.service.update;

import io.reactivesw.cart.application.model.action.SetCustomerId;
import io.reactivesw.cart.domain.model.Cart;
import io.reactivesw.cart.domain.model.LineItem;
import io.reactivesw.cart.domain.service.CartService;
import io.reactivesw.cart.infrastructure.enums.CartStatus;
import io.reactivesw.cart.infrastructure.repository.CartRepository;
import io.reactivesw.cart.infrastructure.update.UpdateAction;
import io.reactivesw.cart.infrastructure.util.CartUpdateActionUtils;
import io.reactivesw.model.Updater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * set customer Id.
 */
@Service(value = CartUpdateActionUtils.SET_CUSTOMER_ID)
public class SetCustomerIdService implements Updater<Cart, UpdateAction> {

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
   * add line item service.
   */
  @Autowired
  private transient AddLineItemService addLineItemService;

  /**
   * set the customer id, then update the price and merge the customer's active cart.
   *
   * @param cart   CartEntity
   * @param action UpdateAction
   */
  @Override
  public void handle(Cart cart, UpdateAction action) {
    SetCustomerId setCustomerId = (SetCustomerId) action;
    String customerId = setCustomerId.getCustomerId();
    Cart existCart = cartService.getActiveCartByCustomerId(customerId);
    if (existCart != null && existCart.getLineItems() != null && !existCart.getLineItems()
        .isEmpty()) {
      mergeCart(cart, existCart);
    }
    cart.setCustomerId(customerId);

    existCart.setCartStatus(CartStatus.Merged);
    cartRepository.save(existCart);
  }

  /**
   * merge this cart.
   *
   * @param originCart Cart
   * @param existCart  Cart
   */
  private void mergeCart(Cart originCart, Cart existCart) {
    List<LineItem> lineItems = existCart.getLineItems();
    lineItems.stream().forEach(
        lineItem -> addLineItemService.addLineItem(originCart, lineItem)
    );
  }
}
