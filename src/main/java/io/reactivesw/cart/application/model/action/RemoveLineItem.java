package io.reactivesw.cart.application.model.action;

import io.reactivesw.cart.infrastructure.util.CartUpdateActionUtils;
import io.reactivesw.cart.infrastructure.update.UpdateAction;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * Decreases the quantity of the given LineItem. If after the update the quantity of the line
 * item is not greater than 0 or the quantity is not specified, the line item is removed from the
 * cart.
 * Created by umasuo on 16/12/5.
 */
@ApiModel
@Data
public class RemoveLineItem implements UpdateAction {

  /**
   * line item id.
   */
  private String lineItemId;

  /**
   * quantity.
   */
  private Integer quantity;

  @Override
  public String getActionName() {
    return CartUpdateActionUtils.REMOVE_LINE_ITEM;
  }
}
