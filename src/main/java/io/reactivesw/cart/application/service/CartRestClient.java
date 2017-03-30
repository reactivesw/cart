package io.reactivesw.cart.application.service;

import io.reactivesw.cart.application.model.ProductView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
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
  @Value("${product.service.uri:http://products/}")
  private transient String productUri;

  /**
   * Gets product data from product service.
   *
   * @param productId the address id
   * @return the Product
   */
  public ProductView getProduct(String productId, Integer variantId) {
    LOG.debug("enter: productId: {}", productId);

    String url = productUri + "CartProducts/" + productId + "?variantId="
        + variantId;
    ProductView product = null;
    try {
      LOG.debug("ProductUri: {}", url);
      product = restTemplate.getForObject(url, ProductView.class);
    } catch (RestClientException ex) {
      LOG.debug("Get Product from product service failed. uri: {}", url);
    }

    LOG.debug("exit: product: {}", product);
    return product;
  }
}
