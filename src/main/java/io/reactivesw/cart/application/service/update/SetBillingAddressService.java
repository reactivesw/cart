package io.reactivesw.cart.application.service.update;

import io.reactivesw.cart.application.model.action.SetBillingAddress;
import io.reactivesw.cart.domain.model.Cart;
import io.reactivesw.cart.infrastructure.util.CartUpdateActionUtils;
import io.reactivesw.cart.infrastructure.update.UpdateAction;
import io.reactivesw.model.Updater;
import org.springframework.stereotype.Service;

/**
 * Created by umasuo on 16/12/19.
 */
@Service(value = CartUpdateActionUtils.SET_BILLING_ADDRESS)
public class SetBillingAddressService implements Updater<Cart, UpdateAction> {


  /**
   * set or unset billing address.
   *
   * @param cart   CartEntity
   * @param action UpdateAction
   */
  @Override
  public void handle(Cart cart, UpdateAction action) {
    SetBillingAddress billingAddress = (SetBillingAddress) action;

    String addressId = billingAddress.getAddressId();

    cart.setBillingAddress(addressId);

  }
}
