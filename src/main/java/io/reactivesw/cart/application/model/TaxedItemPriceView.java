package io.reactivesw.cart.application.model;

import io.reactivesw.model.Money;

import lombok.Data;

/**
 * Created by umasuo on 16/11/17.
 */
@Data
public class TaxedItemPriceView {

  private Money totalNet;

  private Money totalGross;

}
