package io.reactivesw.cart.application.model;

import io.reactivesw.model.Money;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by umasuo on 16/11/17.
 */
@ApiModel(description = "TaxedPriceView fields that can be used in query predicates: totalNet, " +
    "totalGross.")
@Data
public class TaxedPriceView {

  @ApiModelProperty(required = true)
  private Money totalNet;

  @ApiModelProperty(required = true)
  private Money totalGross;

}
