package io.reactivesw.cart.application.service.update;

import io.reactivesw.cart.application.model.action.SetCustomerId;
import io.reactivesw.cart.domain.model.Cart;
import io.reactivesw.cart.infrastructure.util.CartUpdateActionUtils;
import io.reactivesw.cart.infrastructure.update.UpdateAction;
import io.reactivesw.model.Updater;
import org.springframework.stereotype.Service;

/**
 * Sets the customer ID of the cart. When the customer ID is set, the LineItem prices are updated.
 * Customer with the given ID must exist in the merchant.
 * Created by umasuo on 16/12/19.
 */
@Service(value = CartUpdateActionUtils.SET_CUSTOMER_ID)
public class SetCustomerIdService implements Updater<Cart, UpdateAction> {
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
    //TODO if this customer already have an active cart, then merge them.
    cart.setCustomerId(customerId);
  }

}
