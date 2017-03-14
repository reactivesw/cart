package io.reactivesw.cart.application.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.reactivesw.cart.infrastructure.enums.CartState;
import io.reactivesw.cart.infrastructure.enums.InventoryMode;
import io.reactivesw.cart.infrastructure.enums.TaxMode;
import io.reactivesw.model.Money;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Created by umasuo on 16/11/17.
 */
@Data
public class CartView {

  private String id;

  private Integer version;

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
  private ZonedDateTime createdAt;

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
  private ZonedDateTime lastModifiedAt;

  private String customerId;

  private String anonymousId;

  List<LineItemView> lineItems;

  private Money totalPrice;

  private TaxedPriceView taxedPrice;

  private CartState cartState;

  private AddressView shippingAddress;

  private AddressView billingAddress;

  private InventoryMode inventoryMode;

  private TaxMode taxMode;

  private String country;

  private String currencyCode;

  private ShippingInfoView shippingInfo;
}
