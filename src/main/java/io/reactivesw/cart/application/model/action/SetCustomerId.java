package io.reactivesw.cart.application.model.action;

import io.reactivesw.cart.infrastructure.update.UpdateAction;
import io.reactivesw.cart.infrastructure.util.CartUpdateActionUtils;
import lombok.Data;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * Sets the customer ID of the cart.
 * When the customer ID is set, the LineItem prices are updated.(for customer may be in an
 * customer group)
 * <p>
 */
@Data
public class SetCustomerId implements UpdateAction, Serializable {

  /**
   * auto generated serial version.
   */
  private static final long serialVersionUID = 6896135429076233138L;

  /**
   * customer id.
   * If set, a customer with the given ID must exist in the merchant.
   */
  @NotNull
  private String customerId;

  /**
   * get action name.
   *
   * @return
   */
  @Override
  public String getActionName() {
    return CartUpdateActionUtils.SET_CUSTOMER_ID;
  }
}
