package io.reactivesw.cart.application.service.update;

import io.reactivesw.cart.application.model.action.SetShippingAddress;
import io.reactivesw.cart.domain.model.Cart;
import io.reactivesw.cart.infrastructure.util.CartUpdateActionUtils;
import io.reactivesw.cart.infrastructure.update.UpdateAction;
import io.reactivesw.model.Updater;
import org.springframework.stereotype.Service;

/**
 * Sets the shipping address of the cart. Setting the shipping address also sets the TaxRate of
 * the line items and calculates the TaxedPrice. If the address is not provided, the shipping
 * address is unset, the taxedPrice is unset and the taxRates are unset in all line items.
 * Created by umasuo on 16/12/19.
 */
@Service(value = CartUpdateActionUtils.SET_SHIPPING_ADDRESS)
public class SetShippingAddressService implements Updater<Cart, UpdateAction> {

  /**
   * set or unset the shipping address.
   *
   * @param cart   CartEntity
   * @param action UpdateAction
   */
  @Override
  public void handle(Cart cart, UpdateAction action) {
    SetShippingAddress shippingAddress = (SetShippingAddress) action;
    String addressId = shippingAddress.getAddressId();

    cart.setShippingAddress(addressId);
  }
}
