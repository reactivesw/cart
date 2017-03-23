package io.reactivesw.cart.application.service.update;

import io.reactivesw.cart.application.model.action.AddLineItem;
import io.reactivesw.cart.domain.model.Cart;
import io.reactivesw.cart.domain.model.LineItem;
import io.reactivesw.cart.infrastructure.update.UpdateAction;
import io.reactivesw.cart.infrastructure.util.CartUpdateActionUtils;
import io.reactivesw.model.Updater;
import org.springframework.stereotype.Service;

@Service(value = CartUpdateActionUtils.ADD_LINE_ITEM)
public class AddLineItemService implements Updater<Cart, UpdateAction> {

  /**
   * handle add line item action.
   *
   * @param cart   CartEntity
   * @param action UpdateAction
   */
  @Override
  public void handle(Cart cart, UpdateAction action) {
    LineItem item = this.getData((AddLineItem) action);

    addLineItem(cart, item);
  }


  public void addLineItem(Cart cart, LineItem item) {
    LineItem value = cart.getLineItems().stream().filter(
        lineItemValue -> lineItemValue.getProductId().equals(item.getProductId())
            && lineItemValue.getVariantId() == item.getVariantId()
    ).findAny().orElse(null);

    if (value == null) {
      cart.getLineItems().add(item);
    } else {
      //if exist, then just add the quantity
      value.setQuantity(value.getQuantity() + item.getQuantity());
    }
  }

  /**
   * get data from action.
   *
   * @param action AddLineItem
   * @return LineItemValue
   */
  private LineItem getData(AddLineItem action) {
    LineItem item = new LineItem();
    item.setProductId(action.getProductId());
    item.setVariantId(action.getVariantId());
    item.setQuantity(action.getQuantity());

    return item;
  }
}
