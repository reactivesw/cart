package io.reactivesw.cart.application.model;

import io.reactivesw.model.Money;
import io.reactivesw.model.Reference;

import lombok.Data;

/**
 * Created by umasuo on 16/11/17.
 */
@Data
public class ShippingInfoView {

  private Reference shippingMethod;

  private String shippingMethodName;

  private Money price;

  private ShippingRateView shippingRate;

  private TaxedItemPriceView taxedPrice;

  private TaxRateView taxRate;

  private Reference taxCategory;
}
