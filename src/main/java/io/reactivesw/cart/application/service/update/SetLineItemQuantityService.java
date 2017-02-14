package io.reactivesw.cart.application.service.update;

import io.reactivesw.cart.application.model.action.SetLineItemQuantity;
import io.reactivesw.cart.domain.model.Cart;
import io.reactivesw.cart.domain.model.LineItem;
import io.reactivesw.cart.infrastructure.util.CartUpdateActionUtils;
import io.reactivesw.cart.infrastructure.util.UpdateAction;
import io.reactivesw.cart.infrastructure.util.Updater;
import io.reactivesw.exception.NotExistException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by umasuo on 16/12/16.
 */
@Service(value = CartUpdateActionUtils.SET_LINE_ITEM_QUANTITY)
public class SetLineItemQuantityService extends Updater {

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
    if (quantity == null || quantity == 0) {
      entity.getLineItems().remove(item);
    } else {
      item.setQuantity(quantity);
    }
  }

  /**
   * get line item from the cart.
   *
   * @param cart       cart
   * @param lineItemId line item id
   * @return LineItemValue
   */
  private LineItem getLineItem(Cart cart, String lineItemId) {
    Set<LineItem> lineItems = cart.getLineItems();
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
