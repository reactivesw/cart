package io.reactivesw.cart.infrastructure.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * this used to locate each service.
 * TODO this should be replaced by register service.
 * Created by umasuo on 17/1/6.
 */
@Component
public class ServiceLocator {

  /**
   * default url.
   */
  public String defaultUrl = "http://localhost:8088";

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
   * get cart service.
   *
   * @return
   */
  public String getCart() {
    return defaultUrl;
  }

  /**
   * get product service location.
   *
   * @return String
   */
  public String getProduct() {
    return productUri;
  }

  /**
   * get tax category service location.
   *
   * @return String
   */
  public String getTaxCategory() {
    return defaultUrl;
  }

  /**
   * get shipping method method service location.
   *
   * @return String
   */
  public String getShippingMethod() {
    return defaultUrl;
  }

  /**
   * get Zone.
   *
   * @return String
   */
  public String getZone() {
    return defaultUrl;
  }

  /**
   * get Payment.
   *
   * @return String
   */
  public String getPayment() {
    return defaultUrl;
  }

  /**
   * get order.
   *
   * @return String
   */
  public String getOrder() {
    return defaultUrl;
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
