package io.reactivesw.cart.application.service;

import io.reactivesw.cart.application.model.LineItemView;
import io.reactivesw.model.Money;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * line item application.
 */
@Service
public class LineItemApplication {

  /**
   * logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(LineItemApplication.class);


  /**
   * calculate LineItem price.
   *
   * @param item LineItem
   */
  public void calculateItemPrice(LineItemView item) {
    LOG.debug("enter. lineItem: {}", item);
    if (item.getPrice() != null) {
      Money price = item.getPrice();

      int quantity = item.getQuantity() == null ? 0 : item.getQuantity();

      int totalPrice = price.getCentAmount() * quantity;

      Money total = new Money();
      total.setCurrencyCode(price.getCurrencyCode());
      total.setCentAmount(totalPrice);
      item.setTotalPrice(total);
    }
    LOG.debug("exit. lineItem: {}", item);
  }
}
