package io.reactivesw.cart.application.service;

import io.reactivesw.cart.application.model.CartView;
import io.reactivesw.cart.application.model.LineItemView;
import io.reactivesw.cart.application.model.ProductView;
import io.reactivesw.cart.application.model.mapper.CartMapper;
import io.reactivesw.cart.domain.model.Cart;
import io.reactivesw.cart.domain.model.LineItem;
import io.reactivesw.cart.domain.service.CartService;
import io.reactivesw.cart.infrastructure.update.UpdateAction;
import io.reactivesw.cart.infrastructure.util.CreateTimeComparator;
import io.reactivesw.model.Money;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * cart application.
 */
@Service
public class CartApplication {

  /**
   * logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(CartApplication.class);

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
   * cart LineItem service.
   */
  @Autowired
  private transient LineItemApplication lineItemService;

  /**
   * Cart merger.
   */
  @Autowired
  private transient CartMerger cartMerger;

  /**
   * LineItem comparator.
   */
  private transient CreateTimeComparator createTimeComparator = new CreateTimeComparator();

  /**
   * update cart for with action list.
   *
   * @param id      String of cart id
   * @param version Integer
   * @param actions list of actions
   * @return CartEntity
   */
  public CartView updateCart(String id, Integer version, List<UpdateAction> actions) {
    LOG.debug("enter: id{}, version: {}, actions: {}", id, version, actions);

    Cart result = cartService.updateCart(id, version, actions);

    LOG.debug("exit: result: {}", result);
    return getFullCart(result);
  }

  /**
   * Get cart by cart id, and if there is an anonymous id, then try to merge the anonymous cart
   * to customer's cart.
   *
   * @param id cart id
   * @return CartView
   */
  public CartView getCartById(String id, String anonymousId) {
    LOG.debug("Enter. id: {}, anonymousId: {}.", id, anonymousId);
    Cart entity = cartService.getById(id);

    if (anonymousId != null) {
      Cart anonymousCart = cartService.getCartByAnonymousId(anonymousId);

      if (anonymousCart != null
          && anonymousCart.getLineItems() != null
          && !anonymousCart.getLineItems().isEmpty()) {
        cartMerger.mergeCart(entity, anonymousCart);
      } else {
        LOG.debug("Anonymous cart not exist. anonymousId: {}", anonymousId);
      }
    }
    LOG.debug("Exit. cart: {}.", entity);
    return getFullCart(entity);
  }

  /**
   * Get cart by customer id and anonymousId, if anonymous cart exist, then merge it to customer
   * cart.
   *
   * @param customerId  String
   * @param anonymousId String
   * @return CartView
   */
  public CartView getCartByCustomerId(String customerId, String anonymousId) {
    LOG.debug("Enter. customerId: {}, anonymousId: {}.", customerId, anonymousId);
    Cart customerCart = cartService.getActiveCartByCustomerId(customerId);

    if (anonymousId != null) {
      Cart anonymousCart = cartService.getCartByAnonymousId(anonymousId);
      if (anonymousCart != null
          && anonymousCart.getLineItems() != null
          && !anonymousCart.getLineItems().isEmpty()) {
        cartMerger.mergeCart(customerCart, anonymousCart);
      } else {
        LOG.debug("Anonymous cart not exist. anonymousId: {}.", anonymousId);
      }
    }
    LOG.debug("Exit. cart: {}.", customerCart);
    return getFullCart(customerCart);
  }

  /**
   * Get an cart with all the detail info.
   *
   * @param cart CartEntity
   * @return CartView
   */
  public CartView getFullCart(Cart cart) {
    LOG.debug("enter: entity: {}", cart);

    CartView data = fillData(cart);

    calculateCartPrice(data);

    LOG.debug("exit cart: {}", data);
    return data;
  }

  /**
   * fetch data from other service, and then fill it into the cart.
   *
   * @return Cart
   */
  private CartView fillData(Cart cart) {
    LOG.debug("enter: entity: {}", cart);
    //got the base info
    CartView cartView = CartMapper.entityToModel(cart);

    // fill the LineItem info
    fillLineItem(cartView, cart.getLineItems());

    LOG.debug("end fillData, exit: cart: {}", cartView);
    return cartView;
  }


  /**
   * fill the line items' info.
   *
   * @param cartView  Cart
   * @param lineItems list of LineItemValue
   */
  private void fillLineItem(CartView cartView, List<LineItem> lineItems) {
    LOG.debug("Enter. cart: {}, lineItems: {}.", cartView, lineItems);

    if (lineItems != null) {
      List<LineItemView> items = new ArrayList<>();
      lineItems.stream().forEach(
          lineItem -> {

            ProductView product = restClient.getProduct(lineItem.getProductId(), lineItem
                .getVariantId());
            if (product != null) {

              //todo if the product not exit anymore, the auto delete the product?
              LineItemView itemView = new LineItemView();
              itemView.setId(lineItem.getId());
              itemView.setCreatedAt(lineItem.getCreatedAt());
              itemView.setQuantity(lineItem.getQuantity());
              itemView.setProductId(lineItem.getProductId());
              itemView.setVariantId(lineItem.getVariantId());
              itemView.setName(product.getName());
              itemView.setImages(product.getImages());
              itemView.setPrice(product.getPrice().getValue());
              itemView.setSku(product.getSku());
              items.add(itemView);
            }
          }
      );

      // sort the line item with create time.
      Collections.sort(items, createTimeComparator);
      LOG.debug("Exit. cart: {}.", cartView);
      cartView.setLineItems(items);
    }
  }


  /**
   * calculate cart total price.
   *
   * @param cart Cart
   */
  private void calculateCartPrice(CartView cart) {
    LOG.debug("enter: cart: {}", cart);
    List<LineItemView> items = cart.getLineItems();
    int lineItemTotalPrice = 0;
    Money cartTotal = new Money();
    if (items != null) {

      items.stream().forEach(
          lineItem -> {
            lineItemService.calculateItemPrice(lineItem);
            cartTotal.setCurrencyCode(lineItem.getPrice().getCurrencyCode());
          }
      );
      //count total price of all line item
      lineItemTotalPrice = items.stream().mapToInt(
          lineItem -> lineItem.getTotalPrice() == null ? 0 : lineItem.getTotalPrice()
              .getCentAmount()
      ).sum();
    }

    int cartTotalPrice = lineItemTotalPrice;

    // TODO need the currency code?
    cartTotal.setCentAmount(cartTotalPrice);
    cart.setTotalPrice(cartTotal);
    LOG.debug("exit: cart: {}", cart);
  }
}
