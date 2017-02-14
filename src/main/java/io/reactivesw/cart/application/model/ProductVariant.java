package io.reactivesw.cart.application.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by umasuo on 16/11/17.
 */
@Data
@ApiModel
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductVariant {

  @ApiModelProperty(value = "The sequential ID of the variant within the product.", required = true)
  private Integer id;

  @ApiModelProperty(value = "The SKU of the variant.")
  private String sku;

  @ApiModelProperty(value = "User-specific unique identifier for the variant. Product variant " +
      "keys are different from product keys.")
  private String key;

  @ApiModelProperty(value = "The prices of the variant. " +
      "The prices does not contain two prices for the same price scope (same currency, country, " +
      "customer group and channel).")
  private List<Price> prices;

  @ApiModelProperty
  private List<Attribute> attributes;

  @ApiModelProperty(value = "Only appears when price selection is used. This field cannot be used" +
      " in a query predicate.")
  private Price price;

  @ApiModelProperty
  private List<Image> images;

  @ApiModelProperty
  private List<Asset> assets;

}
