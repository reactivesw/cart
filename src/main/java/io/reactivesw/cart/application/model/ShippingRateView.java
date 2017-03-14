package io.reactivesw.cart.application.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.reactivesw.model.Money;

import lombok.Data;

/**
 * Created by umasuo on 16/11/17.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShippingRateView {

  private Money price;

  private Money freeAbove;

}
