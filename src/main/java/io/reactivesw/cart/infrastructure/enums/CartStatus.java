package io.reactivesw.cart.infrastructure.enums;

/**
 * cart status.
 */
public enum CartStatus {

  /**
   * The cart can be updated and ordered. It is the default state.
   */
  Active,

  /**
   * Anonymous cart whose content was merged into a customer cart on signin.
   * No further operations on the cart are allowed.
   */
  Merged,

  /**
   * The cart was ordered. No further operations on the cart are allowed.
   */
  Ordered;

}
