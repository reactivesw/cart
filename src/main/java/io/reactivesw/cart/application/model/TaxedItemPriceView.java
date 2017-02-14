package io.reactivesw.cart.application.model;

import io.reactivesw.model.Money;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by umasuo on 16/11/17.
 */
@ApiModel(description = "TaxedItemPriceView fields can not be used in query predicates.\n")
@Data
public class TaxedItemPriceView {

  @ApiModelProperty(required = true)
  private Money totalNet;

  @ApiModelProperty(required = true)
  private Money totalGross;

}
