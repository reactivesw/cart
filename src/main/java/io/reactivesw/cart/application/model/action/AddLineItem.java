package io.reactivesw.cart.application.model.action;

import io.reactivesw.cart.infrastructure.update.UpdateAction;
import io.reactivesw.cart.infrastructure.util.CartUpdateActionUtils;
import lombok.Data;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class AddLineItem implements UpdateAction, Serializable {

  @NotNull
  private String productId;

  @NotNull
  private Integer variantId;

  @NotNull
  @Min(1)
  private Integer quantity;

  @Override
  public String getActionName() {
    return CartUpdateActionUtils.ADD_LINE_ITEM;
  }
}
