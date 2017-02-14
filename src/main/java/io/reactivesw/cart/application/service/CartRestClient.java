package io.reactivesw.cart.application.service;

import io.reactivesw.cart.application.model.AddressView;
import io.reactivesw.cart.application.model.ProductView;
import io.reactivesw.cart.application.model.TaxCategoryView;
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
   * Gets Address from customer service.
   *
   * @param addressId the address id
   * @return the Address
   */
  public AddressView getAddress(String addressId) {
    LOG.debug("enter: addressId: {}", addressId);

    String url = serviceLocator.getCustomer() + "customers/addresses/" + addressId;
    AddressView address = restTemplate.getForObject(url, AddressView.class);

    LOG.debug("exit: Address: {}", address);
    return address;
  }

  /**
   * Gets product data from product service.
   *
   * @param productId the address id
   * @return the Product
   */
  public ProductView getProduct(String productId) {
    LOG.debug("enter: productId: {}", productId);

    //TODO set up the product service.
    String url = serviceLocator.getProduct() + "/products";
    ProductView product = restTemplate.getForObject(url, ProductView.class);

    LOG.debug("exit: product: {}", product);
    return product;
  }

  /**
   * Gets product data from product service.
   *
   * @param zoneId the zone id
   * @return the Product
   */
//  public Zone getZone(String zoneId) {
//    LOG.debug("enter: zoneId: {}", zoneId);
//    String url = serviceLocator.getZone() + ZoneRouter.getZoneWithId(zoneId);
//    Zone zone = restTemplate.getForObject(url, Zone.class);
//    LOG.debug("exit: zone: {}", zone);
//    return zone;
//  }

  /**
   * Gets tax category id.
   *
   * @param categoryId the tax category id
   * @return the Zone
   */
  public TaxCategoryView getTaxCategory(String categoryId) {
    LOG.debug("enter: categoryId: {}", categoryId);

    String url = serviceLocator.getTaxCategory() + "/categories";
    TaxCategoryView taxCategory = restTemplate.getForObject(url, TaxCategoryView.class);

    LOG.debug("enter: taxCategory: {}", taxCategory);
    return taxCategory;
  }

  /**
   * Gets tax category id.
   *
   * @param methodId the tax category id
   * @return the Zone
   */
//  public ShippingMethod getShippingMethod(String methodId) {
//    LOG.debug("enter: methodId: {}", methodId);
//    String url = serviceLocator.getShippingMethod() + ShippingMethodRouter
//        .getShippingMethodWithId(methodId);
//    ShippingMethod shippingMethod = restTemplate.getForObject(url, ShippingMethod.class);
//    LOG.debug("enter: shippingMethod: {}", shippingMethod);
//    return shippingMethod;
//  }

}
