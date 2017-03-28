package io.reactivesw.cart.application.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.reactivesw.cart.infrastructure.enums.CartStatus;
import io.reactivesw.model.Money;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * cart view.
 */
@Data
public class CartView {

  /**
   * uuid.
   */
  private String id;

  /**
   * version.
   */
  private Integer version;

  /**
   * created time.
   */
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
  private ZonedDateTime createdAt;

  /**
   * last modified time.
   */
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
  private ZonedDateTime lastModifiedAt;

  /**
   * customer id.
   */
  private String customerId;

  /**
   * anonymous id.
   */
  private String anonymousId;

  /**
   * line items.
   */
  private List<LineItemView> lineItems;

  /**
   * total price.
   */
  private Money totalPrice;

  /**
   * cart status.
   */
  private CartStatus cartStatus;
}
