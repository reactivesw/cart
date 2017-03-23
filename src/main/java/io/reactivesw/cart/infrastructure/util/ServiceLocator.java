package io.reactivesw.cart.infrastructure.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * this used to locate each service.
 */
@Component
public class ServiceLocator {

  /**
   * product service uri.
   */
  @Value("${product.service.uri}")
  private String productUri;

  /**
   * customer service uri.
   */
  @Value("${customer.service.uri}")
  private String customerUri;

  /**
   * get product service location.
   *
   * @return String
   */
  public String getProduct() {
    return productUri;
  }

  /**
   * get Customer.
   *
   * @return String
   */
  public String getCustomer() {
    return customerUri;
  }
}
