package io.reactivesw.cart.application.controller;

import io.reactivesw.cart.application.model.CartView;
import io.reactivesw.cart.application.service.CartApplication;
import io.reactivesw.cart.domain.model.Cart;
import io.reactivesw.cart.domain.service.CartService;
import io.reactivesw.cart.infrastructure.Router;
import io.reactivesw.cart.infrastructure.update.UpdateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * cart controller.
 */
@RestController
@CrossOrigin
public class CartController {

  /**
   * logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(CartController.class);

  /**
   * cart service.
   */
  private transient CartService cartService;

  /**
   * cart controller.
   */
  private transient CartApplication cartApplication;

  /**
   * constructor.
   *
   * @param cartService     CartService
   * @param cartApplication CartApplication
   */
  @Autowired
  public CartController(CartService cartService, CartApplication cartApplication) {
    this.cartService = cartService;
    this.cartApplication = cartApplication;
  }

  /**
   * get cart by id.
   *
   * @param cartId      the id
   * @param anonymousId String
   * @return the cart by id
   */
  @GetMapping(Router.CART_WITH_ID)
  public CartView getCartById(@PathVariable(Router.CART_ID) String cartId,
                              @RequestParam(required = false) String anonymousId) {
    LOG.info("Enter, id: {}, anonymousId: {}.", cartId, anonymousId);

    CartView cartView = cartApplication.getCartById(cartId, anonymousId);

    LOG.info("Exit. cartView: {}.", cartView);
    return cartView;
  }

  /**
   * get cart by id.
   * TODO add version here.
   *
   * @param cartId the id
   * @return the cart by id
   */
  @GetMapping(Router.CART_CHECKOUT)
  public CartView checkout(@PathVariable(Router.CART_ID) String cartId) {
    LOG.info("enter. id: {}", cartId);

    Cart entity = cartService.checkout(cartId);

    LOG.info("Exit entity: {}", entity);
    return cartApplication.getFullCart(entity);
  }

  /**
   * get cart by customer id.
   *
   * @param customerId the customer id
   * @return the cart by customer id
   */
  @GetMapping(value = Router.CARTS_ROOT, params = "customerId")
  public CartView getActiveCartByCustomerId(@RequestParam @NotNull String customerId,
                                            @RequestParam(required = false) String anonymousId) {
    LOG.info("Enter. customerId: {}, anonymousId: {}.", customerId);

    CartView cartView = cartApplication.getCartByCustomerId(customerId, anonymousId);

    LOG.info("Exit. cartView: {}.", cartView);
    return cartView;
  }

  /**
   * get cart by customer id.
   *
   * @param anonymousId the customer id
   * @return the cart by customer id
   */
  @GetMapping(value = Router.CARTS_ROOT, params = "anonymousId")
  public CartView getCartByAnonymousId(@RequestParam String anonymousId) {
    LOG.info("anonymousId: {}", anonymousId);

    Cart entity = cartService.getCartByAnonymousId(anonymousId);

    LOG.info("entity:{}", entity);
    return cartApplication.getFullCart(entity);
  }

  /**
   * update cart with cart id.
   *
   * @param id String
   * @return Cart
   */
  @PutMapping(Router.CART_WITH_ID)
  public CartView updateCart(@PathVariable(Router.CART_ID) String id,
                             @RequestBody UpdateRequest updateRequest) {
    LOG.info("id:{}, updateRequest: {}", id, updateRequest);

    CartView result = cartApplication.updateCart(id, updateRequest.getVersion(), updateRequest
        .getActions());

    LOG.info("result:{}", result);
    return result;
  }
}
