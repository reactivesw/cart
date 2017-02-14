package io.reactivesw.cart.application.service.update;

import io.reactivesw.cart.application.model.action.SetShippingMethod;
import io.reactivesw.cart.domain.model.Cart;
import io.reactivesw.cart.domain.model.ShippingInfo;
import io.reactivesw.cart.infrastructure.util.CartUpdateActionUtils;
import io.reactivesw.cart.infrastructure.util.UpdateAction;
import io.reactivesw.cart.infrastructure.util.Updater;
import io.reactivesw.exception.ImmutableException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Sets the ShippingMethod. Prerequisite: The cart must contain a shipping address.
 * Created by umasuo on 16/12/19.
 */
@Service(value = CartUpdateActionUtils.SET_SHIPPING_METHOD)
public class SetShippingMethodService extends Updater {

  /**
   * set the shipping method.
   * An external tax rate can be set if the cart has the External TaxMode.
   *
   * @param cart   CartEntity
   * @param action UpdateAction
   */
  @Override
  public void handle(Cart cart, UpdateAction action) {

    if (Objects.isNull(cart.getShippingAddress())) {
      throw new ImmutableException("Cart must contains a shipping address.");
    }

    SetShippingMethod setShippingMethod = (SetShippingMethod) action;
    String shippingMethodId = setShippingMethod.getShippingMethod() == null ? null :
        setShippingMethod
            .getShippingMethod().getId();

    if (shippingMethodId == null) {
      this.unsetShippingMethod(cart);
    } else {
      this.setShippingMethod(cart, shippingMethodId);
    }

    //TODO set the external tax rate

  }

  /**
   * set shipping method.
   *
   * @param cart             CartEntity
   * @param shippingMethodId String
   */
  private void setShippingMethod(Cart cart, String shippingMethodId) {

    ShippingInfo shippingInfo = this.getShippingInfo(cart);

    shippingInfo.setShippingMethod(shippingMethodId);

    cart.setShippingInfo(shippingInfo);
  }

  /**
   * unset shipping method.
   *
   * @param cart CartEntity
   */
  private void unsetShippingMethod(Cart cart) {

    ShippingInfo shippingInfo = this.getShippingInfo(cart);

    shippingInfo.setShippingMethod(null);

    cart.setShippingInfo(shippingInfo);
  }

  private ShippingInfo getShippingInfo(Cart cart) {
    ShippingInfo shippingInfo = cart.getShippingInfo();
    if (shippingInfo == null) {
      shippingInfo = new ShippingInfo();
      cart.setShippingInfo(shippingInfo);
    }
    return shippingInfo;
  }
}
