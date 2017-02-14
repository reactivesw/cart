package io.reactivesw.cart.application.service.update;

import io.reactivesw.cart.application.model.action.RemoveLineItem;
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
@Service(value = CartUpdateActionUtils.REMOVE_LINE_ITEM)
public class RemoveLineItemService extends Updater {

  /**
   * remove LineItemApplication.
   *
   * @param entity E
   * @param action UpdateAction
   */
  @Override
  public void handle(Cart entity, UpdateAction action) {

    RemoveLineItem removeLineItem = (RemoveLineItem) action;
    String lineItemId = removeLineItem.getLineItemId();
    Integer quantity = removeLineItem.getQuantity();

    Set<LineItem> lineItems = entity.getLineItems();

    LineItem itemValue = this.getLineItem(lineItems, lineItemId);
    Integer currentQuantity = itemValue.getQuantity();

    if (quantity == null || currentQuantity <= quantity) {
      lineItems.remove(itemValue);
    } else {
      Integer remainQuantity = currentQuantity - quantity;
      itemValue.setQuantity(remainQuantity);
    }
    entity.setLineItems(lineItems);
  }

  /**
   * get line item from the cart.
   *
   * @param lineItems  cart
   * @param lineItemId line item id
   * @return LineItemValue
   */
  private LineItem getLineItem(Set<LineItem> lineItems, String lineItemId) {

    LineItem item = lineItems.stream().filter(
        tmpItem -> StringUtils.equals(tmpItem.getId(), lineItemId)
    ).findAny().orElse(null);

    if (item == null) {
      throw new NotExistException("Removing not existing line item.");
    }
    return item;
  }
}
