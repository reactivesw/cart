package io.reactivesw.cart.application.model.action;

import io.reactivesw.cart.infrastructure.update.UpdateAction;
import io.reactivesw.cart.infrastructure.util.CartUpdateActionUtils;
import lombok.Data;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Set the LineItem quantity, minimum is 1.
 * cart.
 * Created by umasuo on 16/12/5.
 */
@Data
public class SetLineItemQuantity implements UpdateAction, Serializable {

  /**
   * line item id.
   * required.
   */
  @NotNull
  private String lineItemId;

  /**
   * quantity.
   * required.
   * the minimum is 1.
   */
  @NotNull
  @Min(1)
  private Integer quantity;

  @Override
  public String getActionName() {
    return CartUpdateActionUtils.SET_LINE_ITEM_QUANTITY;
  }
}
