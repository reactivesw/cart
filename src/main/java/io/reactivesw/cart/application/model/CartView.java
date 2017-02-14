package io.reactivesw.cart.application.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.reactivesw.cart.infrastructure.enums.CartState;
import io.reactivesw.cart.infrastructure.enums.InventoryMode;
import io.reactivesw.cart.infrastructure.enums.TaxMode;
import io.reactivesw.model.Money;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Created by umasuo on 16/11/17.
 */
@Data
@ApiModel
public class CartView {

  @ApiModelProperty(value = "The unique ID of the cart.", required = true)
  private String id;

  @ApiModelProperty(value = "The current version of the cart.", required = true)
  private Integer version;

  @ApiModelProperty(value = "Created date time.", required = true)
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
  private ZonedDateTime createdAt;

  @ApiModelProperty(value = "Last modified date time.", required = true)
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
  private ZonedDateTime lastModifiedAt;

  @ApiModelProperty(value = "Customer Id.")
  private String customerId;

  @ApiModelProperty(value =
      "Identifies cart and order belonging to an anonymous session (the customer has not signed " +
          "up/in yet).")
  private String anonymousId;

  @ApiModelProperty(value = "List of line Item", required = true)
  List<LineItemView> lineItems;

  @ApiModelProperty(value = "Total price", required = true)
  private Money totalPrice;

  @ApiModelProperty(value =
      "Not set until the shipping address is set. Will be set automatically in the Platform " +
          "TaxMode. For the External tax mode it will be set as soon as the external tax rates " +
          "for all line items, custom line items, and shipping in the cart are set.")
  private TaxedPriceView taxedPrice;

  @ApiModelProperty(value = "CartView State", required = true)
  private CartState cartState;

  @ApiModelProperty(value = "The shipping address is also used to determine tax rate of the line " +
      "items.")
  private AddressView shippingAddress;

  @ApiModelProperty(value = "Billing Address.")
  private AddressView billingAddress;

  @ApiModelProperty(value = "Inventory Mode", required = true)
  private InventoryMode inventoryMode;

  @ApiModelProperty(value = "Tax Mode", required = true)
  private TaxMode taxMode;

  @ApiModelProperty(value = "A two-digit country code as per ↗ ISO 3166-1 alpha-2 . Used for " +
      "product variant price selection.")
  private String country;

  @ApiModelProperty(value = "currency code for this cart")
  private String currencyCode;

  @ApiModelProperty(value = "Set automatically once the ShippingMethod is set.")
  private ShippingInfoView shippingInfo;

}
