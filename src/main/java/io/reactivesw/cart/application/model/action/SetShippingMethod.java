package io.reactivesw.cart.application.model.action;

import io.reactivesw.cart.infrastructure.util.CartUpdateActionUtils;
import io.reactivesw.cart.infrastructure.util.UpdateAction;
import io.reactivesw.model.Reference;
import lombok.Data;

/**
 * Created by umasuo on 16/12/15.
 */
@Data
public class SetShippingMethod implements UpdateAction {

  /**
   * Reference to a ShippingMethod.
   */
  private Reference shippingMethod;

  @Override
  public String getActionName() {
    return CartUpdateActionUtils.SET_SHIPPING_METHOD;
  }
}
