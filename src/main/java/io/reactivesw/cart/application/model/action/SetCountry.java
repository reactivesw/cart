package io.reactivesw.cart.application.model.action;

import io.reactivesw.cart.infrastructure.util.CartUpdateActionUtils;
import io.reactivesw.cart.infrastructure.update.UpdateAction;
import lombok.Data;

/**
 * Created by umasuo on 16/12/15.
 */
@Data
public class SetCountry implements UpdateAction {

  /**
   * A two-digit country code as per ↗ ISO 3166-1 alpha-2 .
   */
  private String country;

  @Override
  public String getActionName() {
    return CartUpdateActionUtils.SET_COUNTRY;
  }
}
