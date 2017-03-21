package io.reactivesw.cart.application.model;

import io.reactivesw.model.LocalizedString;
import io.reactivesw.model.Money;
import io.reactivesw.model.Reference;

import lombok.Data;

import javax.persistence.Id;

/**
 * Created by umasuo on 16/11/17.
 */
@Data
public class LineItemView {

  @Id
  private String id;

  private String productId;

  private LocalizedString name;

  private String slug;

  private ProductVariantView productVariant;

  private PriceView price;

  private TaxedItemPriceView taxedPrice;

  private Money totalPrice;

  private Integer quantity;

  private TaxRateView taxRate;

  private Reference supplyChannel;

  private Reference distributionChannel;
}
