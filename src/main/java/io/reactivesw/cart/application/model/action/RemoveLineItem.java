package io.reactivesw.cart.application.model.action;

import io.reactivesw.cart.infrastructure.update.UpdateAction;
import io.reactivesw.cart.infrastructure.util.CartUpdateActionUtils;
import lombok.Data;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Decreases the quantity of the given LineItem. If after the update the quantity of the line
 * item is not greater than 0 or the quantity is not specified, the line item is removed from the
 * cart.
 * Created by umasuo on 16/12/5.
 */
@Data
public class RemoveLineItem implements UpdateAction, Serializable {

  /**
   * line item id.
   */
  @NotNull
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
