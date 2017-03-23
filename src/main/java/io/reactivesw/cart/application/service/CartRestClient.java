package io.reactivesw.cart.application.service;

import io.reactivesw.cart.application.model.ProductView;
import io.reactivesw.cart.infrastructure.util.ServiceLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Davis on 16/12/22.
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
   * service locator.
   */
  @Autowired
  private transient ServiceLocator serviceLocator;


  /**
   * Gets product data from product service.
   *
   * @param productId the address id
   * @return the Product
   */
  public ProductView getProduct(String productId, Integer variantId) {
    LOG.debug("enter: productId: {}", productId);

    String url = serviceLocator.getProduct() + "CartProducts/" + productId + "?variantId=" +
        variantId;
    ProductView product = restTemplate.getForObject(url, ProductView.class);

    LOG.debug("exit: product: {}", product);
    return product;
  }

}
