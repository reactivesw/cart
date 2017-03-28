package io.reactivesw.cart.application.service;

import io.reactivesw.cart.application.model.ProductView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * cart rest client.
 */
@Component
public class CartRestClient {

  /**
   * logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(CartRestClient.class);

  /**
   * RestTemplate.
   */
  private transient RestTemplate restTemplate = new RestTemplate();

  /**
   * product uri.
   */
  @Value("{product.service.uri}")
  private transient String productUri;

  /**
   * Gets product data from product service.
   *
   * @param productId the address id
   * @return the Product
   */
  public ProductView getProduct(String productId, Integer variantId) {
    LOG.debug("enter: productId: {}", productId);

    String url = productUri + "CartProducts/" + productId + "?variantId=" +
        variantId;
    ProductView product = restTemplate.getForObject(url, ProductView.class);

    LOG.debug("exit: product: {}", product);
    return product;
  }

}
