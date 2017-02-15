package io.reactivesw.cart.application.service.update;

import io.reactivesw.cart.application.model.action.SetCountry;
import io.reactivesw.cart.domain.model.Cart;
import io.reactivesw.cart.infrastructure.util.CartUpdateActionUtils;
import io.reactivesw.cart.infrastructure.update.UpdateAction;
import io.reactivesw.model.Updater;
import org.springframework.stereotype.Service;

/**
 * Sets the country of the cart. When the country is set, the LineItem prices are updated.
 * Created by umasuo on 16/12/19.
 */
@Service(value = CartUpdateActionUtils.SET_COUNTRY)
public class SetCountryService implements Updater<Cart, UpdateAction> {


  /**
   * set or unset the country.
   *
   * @param cart   CartEntity
   * @param action UpdateAction
   */
  @Override
  public void handle(Cart cart, UpdateAction action) {
    SetCountry setCountry = (SetCountry) action;
    String country = setCountry.getCountry();
    //TODO check the correction of the country
    cart.setCountry(country);
  }
}
