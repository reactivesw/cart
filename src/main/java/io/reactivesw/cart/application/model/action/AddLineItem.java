package io.reactivesw.cart.application.model.action;

import io.reactivesw.cart.application.model.LineItemDraft;
import io.reactivesw.cart.infrastructure.util.CartUpdateActionUtils;
import io.reactivesw.cart.infrastructure.update.UpdateAction;
import io.swagger.annotations.ApiModel;

/**
 * Created by umasuo on 16/12/1.
 */
@ApiModel
public class AddLineItem extends LineItemDraft implements UpdateAction {
  @Override
  public String getActionName() {
    return CartUpdateActionUtils.ADD_LINE_ITEM;
  }
}
