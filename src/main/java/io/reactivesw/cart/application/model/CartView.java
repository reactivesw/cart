package io.reactivesw.cart.application.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.reactivesw.cart.infrastructure.enums.CartState;
import io.reactivesw.model.Money;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

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

  private List<LineItemView> lineItems;

  private Money totalPrice;

  private CartState cartState;
}
