package io.reactivesw.cart.application.service.update;

import io.reactivesw.cart.application.model.action.SetCustomerId;
import io.reactivesw.cart.domain.model.Cart;
import io.reactivesw.cart.domain.model.LineItem;
import io.reactivesw.cart.domain.service.CartService;
import io.reactivesw.cart.infrastructure.enums.CartState;
import io.reactivesw.cart.infrastructure.repository.CartRepository;
import io.reactivesw.cart.infrastructure.util.CartUpdateActionUtils;
import io.reactivesw.cart.infrastructure.update.UpdateAction;
import io.reactivesw.model.Updater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = CartUpdateActionUtils.SET_CUSTOMER_ID)
public class SetCustomerIdService implements Updater<Cart, UpdateAction> {

  /**
   * cart service.
   */
  @Autowired
  private transient CartService cartService;

  @Autowired
  private transient CartRepository cartRepository;

  @Autowired
  private AddLineItemService addLineItemService;
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

    existCart.setCartState(CartState.Merged);
    cartRepository.save(existCart);
  }

  /**
   * merge this cart.
   *
   * @param originCart
   * @param existCart
   */
  private void mergeCart(Cart originCart, Cart existCart) {
    List<LineItem> lineItems = existCart.getLineItems();
    lineItems.stream().forEach(
        lineItem -> addLineItemService.addLineItem(originCart,lineItem)
    );
  }
}
