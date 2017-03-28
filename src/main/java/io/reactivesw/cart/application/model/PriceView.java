package io.reactivesw.cart.application.model;

import io.reactivesw.model.Money;
import lombok.Data;

/**
 * price view.
 */
@Data
public class PriceView {

  /**
   * uuid.
   */
  private String id;

  /**
   * value.
   */
  private Money value;

}
