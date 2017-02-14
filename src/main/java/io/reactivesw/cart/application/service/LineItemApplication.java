package io.reactivesw.cart.application.service;

import io.reactivesw.cart.application.model.LineItemView;
import io.reactivesw.cart.application.model.PriceView;
import io.reactivesw.cart.application.model.ProductVariantView;
import io.reactivesw.model.Money;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by umasuo on 16/12/12.
 */
@Service
public class LineItemApplication {

  /**
   * logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(LineItemApplication.class);

  /**
   * select Price for the item from variant prices.
   * This selection based on the currency, country
   *
   * @param item         LineItem
   * @param currencyCode currency code of this cart.
   * @param country      country of the cart
   */
  public void selectItemPrice(LineItemView item, String currencyCode, String country) {
    LOG.debug("enter: LineItem: {}, currencyCode: {}, country: {}", item, currencyCode, country);
    ProductVariantView variant = item.getProductVariant();
    if (variant != null) {
      List<PriceView> prices = variant.getPrices();

      PriceView selectedPrice = prices.parallelStream().filter(
          price -> {
            String savedCurrencyCode = price.getValue().getCurrencyCode();
            String savedCountry = price.getCountry();
            boolean onlyCurrency = StringUtils.equals(currencyCode, savedCurrencyCode)
                && country == null;
            boolean bothCurrencyAndCountry = StringUtils.equals(currencyCode, savedCurrencyCode)
                && StringUtils.equals(country, savedCountry);

            return onlyCurrency | bothCurrencyAndCountry;
          }
      ).findAny().orElse(null);

      item.setPrice(selectedPrice);
    }
  }


  /**
   * calculate LineItem price.
   *
   * @param item LineItem
   */
  public void calculateItemPrice(LineItemView item) {
    if (item.getPrice() != null) {
      Money price = item.getPrice().getValue();
      int quantity = item.getQuantity() == null ? 0 : item.getQuantity();
      Money total = new Money();
      total.setCurrencyCode(price.getCurrencyCode());
      int totalPrice = price.getCentAmount() * quantity;
      total.setCentAmount(totalPrice);
      item.setTotalPrice(total);
      //TODO for tax price, this should split to an single function
    }
  }
}
