package io.reactivesw.cart.application.service.update;

import io.reactivesw.cart.application.model.action.SetTaxMode;
import io.reactivesw.cart.domain.model.Cart;
import io.reactivesw.cart.infrastructure.enums.TaxMode;
import io.reactivesw.cart.infrastructure.util.CartUpdateActionUtils;
import io.reactivesw.cart.infrastructure.util.UpdateAction;
import io.reactivesw.cart.infrastructure.util.Updater;
import org.springframework.stereotype.Service;

/**
 * Changes the TaxMode of a cart. When a tax mode is changed from External to Platform or
 * Disabled, all previously set external tax rates will be removed. When changing the tax mode to
 * Platform, line items, custom line items and shipping method require a tax category with a tax
 * rate for the given shipping address.
 * Created by umasuo on 16/12/19.
 */
@Service(value = CartUpdateActionUtils.SET_TAX_MODE)
public class SetTaxModeService extends Updater {


  /**
   * change the tax mode.
   *
   * @param cart   CartEntity
   * @param action UpdateAction
   */
  @Override
  public void handle(Cart cart, UpdateAction action) {
    SetTaxMode changeTaxMode = (SetTaxMode) action;

    TaxMode taxMode = changeTaxMode.getTaxMode();

    cart.setTaxMode(taxMode);
  }
}
