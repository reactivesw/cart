package io.reactivesw.cart.application.service.update;

import io.reactivesw.cart.application.model.LineItemDraft;
import io.reactivesw.cart.application.model.action.AddLineItem;
import io.reactivesw.cart.application.model.mapper.LineItemMapper;
import io.reactivesw.cart.domain.model.Cart;
import io.reactivesw.cart.domain.model.LineItem;
import io.reactivesw.cart.infrastructure.util.CartUpdateActionUtils;
import io.reactivesw.cart.infrastructure.update.UpdateAction;
import io.reactivesw.model.Updater;
import org.springframework.stereotype.Service;

/**
 * Created by umasuo on 16/12/16.
 */
@Service(value = CartUpdateActionUtils.ADD_LINE_ITEM)
public class AddLineItemService implements Updater<Cart, UpdateAction> {

  /**
   * handle add line item action.
   *
   * @param entity CartEntity
   * @param action UpdateAction
   */
  @Override
  public void handle(Cart entity, UpdateAction action) {
    LineItem data = this.getData((AddLineItem) action);

    LineItem value = entity.getLineItems().stream().filter(
        lineItemValue -> lineItemValue.getProductId().equals(data.getProductId())
            && lineItemValue.getVariant() == data.getVariant()
    ).findAny().orElse(null);

    if (value == null) {
      entity.getLineItems().add(data);
    } else {
      //if exist, then just add the quantity
      value.setQuantity(value.getQuantity() + data.getQuantity());
    }
  }

  /**
   * get data from action.
   *
   * @param action AddLineItem
   * @return LineItemValue
   */
  private LineItem getData(AddLineItem action) {
    return LineItemMapper.modelToEntity((LineItemDraft) action);
  }
}
