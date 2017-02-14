package io.reactivesw.cart.application.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by umasuo on 16/11/17.
 */
@Data
@ApiModel(description = "A SubRateEntity is used to calculate the taxPortions field in a cart or " +
    "order. It is useful if the total tax of a country is a combination of multiple taxes (e.g. " +
    "state and local taxes).")
public class SubRate {


  @ApiModelProperty(required = true)
  private String name;

  @ApiModelProperty(value = "Number Percentage in the range of [0..1].", required = true)
  private Float amount;
}
