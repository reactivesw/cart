package io.reactivesw.cart.application.model.action;

import io.reactivesw.cart.infrastructure.util.CartUpdateActionUtils;
import io.reactivesw.cart.infrastructure.update.UpdateAction;
import lombok.Data;

/**
 * Created by umasuo on 16/12/15.
 */
@Data
public class SetBillingAddress implements UpdateAction {

  /**
   * billing address.
   */
  private String addressId;

  @Override
  public String getActionName() {
    return CartUpdateActionUtils.SET_BILLING_ADDRESS;
  }
}
