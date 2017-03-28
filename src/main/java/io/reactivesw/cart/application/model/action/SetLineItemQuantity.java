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
 */
@Data
public class SetLineItemQuantity implements UpdateAction, Serializable {

  /**
   * auto generated time.
   */
  private static final long serialVersionUID = -8456086644392778829L;

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

  /**
   * get action name.
   *
   * @return
   */
  @Override
  public String getActionName() {
    return CartUpdateActionUtils.SET_LINE_ITEM_QUANTITY;
  }
}
