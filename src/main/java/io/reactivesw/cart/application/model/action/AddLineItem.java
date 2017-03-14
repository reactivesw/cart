package io.reactivesw.cart.application.model.action;

import io.reactivesw.cart.application.model.LineItemDraft;
import io.reactivesw.cart.infrastructure.update.UpdateAction;
import io.reactivesw.cart.infrastructure.util.CartUpdateActionUtils;

/**
 * Created by umasuo on 16/12/1.
 */
public class AddLineItem extends LineItemDraft implements UpdateAction {
  @Override
  public String getActionName() {
    return CartUpdateActionUtils.ADD_LINE_ITEM;
  }
}
