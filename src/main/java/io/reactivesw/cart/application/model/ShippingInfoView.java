package io.reactivesw.cart.application.model;

import io.reactivesw.model.Money;
import io.reactivesw.model.Reference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by umasuo on 16/11/17.
 */
@Data
@ApiModel
public class ShippingInfoView {

  @ApiModelProperty(value = "Reference to a ShippingMethod, Not set if custom shipping method is " +
      "used.")
  private Reference shippingMethod;

  @ApiModelProperty(required = true)
  private String shippingMethodName;

  @ApiModelProperty(value = "Determined based on the ShippingRate and the sum of LineItemView prices.",
      required = true)
  private Money price;

  @ApiModelProperty(value = "The shipping rate used to determine the price.", required = true)
  private ShippingRate shippingRate;

  @ApiModelProperty(value = "Set once the taxRate is set.")
  private TaxedItemPriceView taxedPrice;

  @ApiModelProperty(value = "Will be set automatically in the Platform TaxMode once the shipping " +
      "address is set is set. For the External tax mode the tax rate has to be set explicitly " +
      "with the ExternalTaxRateDraft.")
  private TaxRate taxRate;

  @ApiModelProperty(value = "Reference to a TaxCategory")
  private Reference taxCategory;
}
