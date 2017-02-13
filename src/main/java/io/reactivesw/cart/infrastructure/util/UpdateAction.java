package io.reactivesw.cart.infrastructure.util;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * configurations for common update actions that will be used in more thant one service
 * and this action also extends other action configure in each service.
 * Created by umasuo on 16/11/21.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property =
    "action")
@JsonSubTypes( {
//    @JsonSubTypes.Type(value = SetCustomerPaymentId.class, name = "setCustomerPaymentId"),
})
public interface UpdateAction {
  String getActionName();
}
