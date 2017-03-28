package io.reactivesw.cart.application.service.update;

import io.reactivesw.cart.application.model.action.SetLineItemQuantity;
import io.reactivesw.cart.domain.model.Cart;
import io.reactivesw.cart.domain.model.LineItem;
import io.reactivesw.cart.infrastructure.update.UpdateAction;
import io.reactivesw.cart.infrastructure.util.CartUpdateActionUtils;
import io.reactivesw.exception.NotExistException;
import io.reactivesw.model.Updater;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * set line item quantity service.
 */
@Service(value = CartUpdateActionUtils.SET_LINE_ITEM_QUANTITY)
public class SetLineItemQuantityService implements Updater<Cart, UpdateAction> {

  /**
   * change line item quantity.
   *
   * @param entity E
   * @param action UpdateAction
   */
  @Override
  public void handle(Cart entity, UpdateAction action) {
    SetLineItemQuantity quantityAction = (SetLineItemQuantity) action;
    Integer quantity = quantityAction.getQuantity();
    String lineItemId = quantityAction.getLineItemId();

    LineItem item = this.getLineItem(entity, lineItemId);
    item.setQuantity(quantity);
  }

  /**
   * get line item from the cart.
   *
   * @param cart       cart
   * @param lineItemId line item id
   * @return LineItemValue
   */
  private LineItem getLineItem(Cart cart, String lineItemId) {
    List<LineItem> lineItems = cart.getLineItems();
    LineItem item = lineItems.stream().filter(
        tmpItem -> StringUtils.equals(tmpItem.getId(), lineItemId)
    ).findAny().orElse(null);
    if (item == null) {
      throw new NotExistException("LineItem not existing when for cartId: " + cart.getId() +
          "lineItemId:" + lineItemId);
    }
    return item;
  }
}
