package io.reactivesw.cart.infrastructure.update;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.reactivesw.cart.application.model.action.AddLineItem;
import io.reactivesw.cart.application.model.action.RemoveLineItem;
import io.reactivesw.cart.application.model.action.SetBillingAddress;
import io.reactivesw.cart.application.model.action.SetCountry;
import io.reactivesw.cart.application.model.action.SetCustomerId;
import io.reactivesw.cart.application.model.action.SetLineItemQuantity;
import io.reactivesw.cart.application.model.action.SetShippingAddress;
import io.reactivesw.cart.application.model.action.SetShippingMethod;
import io.reactivesw.cart.application.model.action.SetTaxMode;

/**
 * configurations for common update actions that will be used in more thant one service
 * and this action also extends other action configure in each service.
 * Created by umasuo on 16/11/21.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property =
    "action")
@JsonSubTypes( {
    @JsonSubTypes.Type(value = AddLineItem.class, name = "addLineItem"),
    @JsonSubTypes.Type(value = RemoveLineItem.class, name = "removeLineItem"),
    @JsonSubTypes.Type(value = SetBillingAddress.class, name = "setBillingAddress"),
    @JsonSubTypes.Type(value = SetCountry.class, name = "setCountry"),
    @JsonSubTypes.Type(value = SetCustomerId.class, name = "setSubjectId"),
    @JsonSubTypes.Type(value = SetLineItemQuantity.class, name = "setLineItemQuantity"),
    @JsonSubTypes.Type(value = SetShippingAddress.class, name = "setShippingAddress"),
    @JsonSubTypes.Type(value = SetShippingMethod.class, name = "setShippingMethod"),
    @JsonSubTypes.Type(value = SetTaxMode.class, name = "setTaxMode")
})
public interface UpdateAction {
  String getActionName();
}
