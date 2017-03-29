package io.reactivesw.cart.infrastructure.update;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.reactivesw.cart.application.model.action.AddLineItem;
import io.reactivesw.cart.application.model.action.RemoveLineItem;
import io.reactivesw.cart.application.model.action.SetCustomerId;
import io.reactivesw.cart.application.model.action.SetLineItemQuantity;
import io.reactivesw.cart.infrastructure.util.CartUpdateActionUtils;

/**
 * configurations for common update actions that will be used in more thant one service
 * and this action also extends other action configure in each service.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property =
    "action")
@JsonSubTypes( {
    @JsonSubTypes.Type(value = AddLineItem.class, name = CartUpdateActionUtils.ADD_LINE_ITEM),
    @JsonSubTypes.Type(value = RemoveLineItem.class, name = CartUpdateActionUtils.REMOVE_LINE_ITEM),
    @JsonSubTypes.Type(value = SetCustomerId.class, name = CartUpdateActionUtils.SET_CUSTOMER_ID),
    @JsonSubTypes.Type(value = SetLineItemQuantity.class, name = CartUpdateActionUtils
        .SET_LINE_ITEM_QUANTITY)})
public interface UpdateAction {
  /**
   * get action name.
   *
   * @return String.
   */
  String getActionName();
}
