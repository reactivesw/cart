package io.reactivesw.cart.application.model.action;

import io.reactivesw.cart.infrastructure.update.UpdateAction;
import io.reactivesw.cart.infrastructure.util.CartUpdateActionUtils;
import lombok.Data;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * add line item.
 */
@Data
public class AddLineItem implements UpdateAction, Serializable {

  /**
   * auto generated serial version.
   */
  private static final long serialVersionUID = -2146127477975618855L;

  /**
   * product id.
   */
  @NotNull
  private String productId;

  /**
   * variant id.
   */
  @NotNull
  private Integer variantId;

  /**
   * quantity.
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
    return CartUpdateActionUtils.ADD_LINE_ITEM;
  }
}
