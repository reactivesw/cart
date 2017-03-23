package io.reactivesw.cart.application.service;

import io.reactivesw.cart.application.model.AddressView;
import io.reactivesw.cart.application.model.CartView;
import io.reactivesw.cart.application.model.LineItemView;
import io.reactivesw.cart.application.model.ProductVariantView;
import io.reactivesw.cart.application.model.ProductView;
import io.reactivesw.cart.application.model.mapper.CartMapper;
import io.reactivesw.cart.application.model.mapper.LineItemMapper;
import io.reactivesw.cart.domain.model.Cart;
import io.reactivesw.cart.domain.model.LineItem;
import io.reactivesw.cart.domain.model.ShippingInfo;
import io.reactivesw.cart.domain.service.CartService;
import io.reactivesw.cart.infrastructure.update.UpdateAction;
import io.reactivesw.cart.infrastructure.update.UpdaterService;
import io.reactivesw.cart.infrastructure.util.ReferenceTypes;
import io.reactivesw.model.Money;
import io.reactivesw.model.Reference;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by umasuo on 16/12/28.
 */
@Service
public class CartApplication {

  /**
   * logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(CartApplication.class);

  /**
   * cart service.
   */
  @Autowired
  private transient CartService cartService;

  /**
   * rest client.
   */
  @Autowired
  private transient CartRestClient restClient;

  /**
   * cart update service.
   */
  @Autowired
  private transient UpdaterService cartUpdater;

  /**
   * cart LineItem service.
   */
  @Autowired
  private transient LineItemApplication lineItemService;

  /**
   * update cart for with action list.
   *
   * @param id      String of cart id
   * @param version Integer
   * @param actions list of actions
   * @return CartEntity
   */
  public CartView updateCart(String id, Integer version, List<UpdateAction> actions) {
    LOGGER.debug("enter: id{}, version: {}, actions: {}", id, version, actions);
    Cart cart = cartService.getById(id);

    //update data from action
    actions.stream().forEach(
        action -> cartUpdater.handle(cart, action)
    );

    Cart result = cartService.updateCart(id, version, cart);

    LOGGER.debug("exit: result: {}", result);
    return getFullCart(result);
  }

  /**
   * get an cart with all the detail info.
   *
   * @param cart CartEntity
   * @return CartView
   */
  public CartView getFullCart(Cart cart) {
    LOGGER.debug("enter: entity: {}", cart);

    CartView data = fillData(cart);

    calculateCartPrice(data);

    LOGGER.debug("exit cart: {}", data);
    return data;
  }

  /**
   * fetch data from other service, and then fill it into the cart
   *
   * @return Cart
   */
  private CartView fillData(Cart cart) {
    LOGGER.debug("enter: entity: {}", cart);
    //got the base info
    CartView cartView = CartMapper.entityToModel(cart);

    // Attention: we should set address first, because Tax category should use the shipping address.

    //fill the address info: shipping address, billing address
    fillShippingAddress(cartView, cart.getShippingAddress());
    fillBillingAddress(cartView, cart.getBillingAddress());

    // fill the LineItem info
    fillLineItem(cartView, cart.getLineItems());

    //fill the shipping info
    fillShippingInfo(cartView, cart.getShippingInfo());

    LOGGER.debug("end fillData, exit: cart: {}", cartView);
    return cartView;
  }


  /**
   * get shipping address from customer service.
   *
   * @param cartView  Cart
   * @param addressId address id.
   */
  private void fillShippingAddress(CartView cartView, String addressId) {
    LOGGER.debug("enter: cart: {}, addressId: {}", cartView, addressId);

    if (StringUtils.isNotBlank(addressId)) {
      //get address from customer service
      AddressView address = restClient.getAddress(addressId);
      cartView.setShippingAddress(address);

      LOGGER.debug("enter: cart: {}, address: {}", cartView, address);
    }
  }

  /**
   * get billing address from customer service.
   *
   * @param cartView  Cart
   * @param addressId String address id
   */
  private void fillBillingAddress(CartView cartView, String addressId) {
    LOGGER.debug("enter: cart: {}, addressId: {}", cartView, addressId);

    if (StringUtils.isNotBlank(addressId)) {
      //get address from customer service
      AddressView address = restClient.getAddress(addressId);
      cartView.setBillingAddress(address);

      LOGGER.debug("enter: cart: {}, addressId: {}", cartView, addressId);
    }
  }


  /**
   * fill the line items' info.
   *
   * @param cartView  Cart
   * @param lineItems Set<LineItemValue>
   */
  private void fillLineItem(CartView cartView, Set<LineItem> lineItems) {
    LOGGER.debug("enter: cart: {}, lineItems: {}", cartView, lineItems);

    if (lineItems != null) {
      List<LineItemView> items = new ArrayList<>();
      lineItems.parallelStream().forEach(
          lineItem -> {
            LineItemView item = new LineItemView();
            setLineItemBaseInfo(item, lineItem);
            ProductView product = restClient.getProduct(item.getProductId());
            LineItemMapper.fillView(item, product);
          }
      );
      LOGGER.debug("exit: cart: {}", cartView);
      cartView.setLineItems(items);
    }
  }

  /**
   * set id, quantity, product id, distribution channel, and supply channel that save in CartEntity.
   *
   * @param item          LineItem
   * @param lineItemValue LineItemEntity
   */
  private void setLineItemBaseInfo(LineItemView item, LineItem lineItemValue) {
    LOGGER.debug("enter: LineItem: {}, LineItemValue: {}", item, lineItemValue);

    item.setId(lineItemValue.getId());
    item.setQuantity(lineItemValue.getQuantity());
    item.setProductId(lineItemValue.getProductId());
    item.setDistributionChannel(new Reference(ReferenceTypes.CHANNEL.getType(),
        lineItemValue.getDistributionChannel()));
    item.setSupplyChannel(new Reference(ReferenceTypes.CHANNEL.getType(),
        lineItemValue.getSupplyChannel()));

    LOGGER.debug("exit: LineItem: {}", item);
  }

  /**
   * set the product info of this lineItem. product name, slug, and selected variant.
   *
   * @param item        LineItem
   * @param productData ProductView
   * @param variantId   Integer of variantId
   */
  private void setLineItemProductInfo(LineItemView item, ProductView productData, Integer
      variantId) {
    LOGGER.debug("enter: LineItem: {}, productData: {}, variantId: {}", item, productData,
        variantId);
    item.setName(productData.getName());
    item.setSlug(productData.getSlug());

    ProductVariantView variant = productData.getVariant();
    item.setProductVariant(variant);

    LOGGER.debug("exit: LineItem: {}", item);
  }

  /**
   * fill shipping info if the shipping method has been set
   *
   * @param cart      Cart
   * @param infoValue ShippingInfoValue
   */
  private void fillShippingInfo(CartView cart, ShippingInfo infoValue) {
    LOGGER.debug("enter: cart: {}, ShippingInfoValue: {}", cart, infoValue);

    String shippingMethodId = infoValue == null ? null : infoValue.getShippingMethod();
    if (shippingMethodId != null) {
      //TODO fill the function
    }
    LOGGER.debug("exit: cart: {}", cart);
  }


  /**
   * calculate cart total price
   *
   * @param cart Cart
   */
  private void calculateCartPrice(CartView cart) {
    LOGGER.debug("enter: cart: {}", cart);
    String currencyCode = cart.getCurrencyCode();
    String country = cart.getCountry();
    List<LineItemView> items = cart.getLineItems();
    int lineItemTotalPrice = 0;
    if (items != null) {
      items.stream().forEach(
          lineItem -> {
            lineItemService.selectItemPrice(lineItem, currencyCode, country);
            lineItemService.calculateItemPrice(lineItem);
          }
      );
      //count total price of all line item
      lineItemTotalPrice = items.stream().mapToInt(
          lineItem -> lineItem.getTotalPrice() == null ? 0 : lineItem.getTotalPrice()
              .getCentAmount()
      ).sum();
    }

    //TODO select and calculate shipping price

    //TODO total price is lineItemTotalPrice + shippingPrice
    int cartTotalPrice = lineItemTotalPrice;
    Money cartTotal = new Money();
    cartTotal.setCurrencyCode(cart.getCurrencyCode());
    cartTotal.setCentAmount(cartTotalPrice);
    cart.setTotalPrice(cartTotal);
    LOGGER.debug("exit: cart: {}", cart);
  }
}
