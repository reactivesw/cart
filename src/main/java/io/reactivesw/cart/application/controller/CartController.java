package io.reactivesw.cart.application.controller;

import io.reactivesw.cart.application.model.CartView;
import io.reactivesw.cart.application.service.CartApplication;
import io.reactivesw.cart.domain.model.Cart;
import io.reactivesw.cart.domain.service.CartService;
import io.reactivesw.cart.infrastructure.Router;
import io.reactivesw.cart.infrastructure.util.UpdateRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by umasuo on 16/11/21.
 */
@RestController
public class CartController {

  /**
   * logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(CartController.class);

  /**
   * cart service.
   */
  @Autowired
  private transient CartService cartService;

  /**
   * cart application.
   */
  @Autowired
  private transient CartApplication cartApplication;

  /**
   * get cart by id.
   *
   * @param id the id
   * @return the cart by id
   */
  @ApiOperation(value = "Get cart by cart id.")
  @GetMapping(Router.CART_WITH_ID)
  public CartView getCartById(@ApiParam(required = true) @PathVariable(Router.CART_ID) String id) {
    LOG.info("cartId:{}", id);

    Cart entity = cartService.getById(id);

    LOG.info("entity:{}", entity);
    return cartApplication.getFullCart(entity);
  }

  /**
   * get cart by customer id.
   *
   * @param customerId the customer id
   * @return the cart by customer id
   */
  @ApiOperation(value = "get cart by customer id")
  @GetMapping(value = Router.CARTS_ROOT, params = "subjectId")
  public CartView getActiveCartByCustomerId(@ApiParam(value = "subjectId") @RequestParam String
                                                customerId) {
    LOG.info("subjectId:{}", customerId);

    Cart entity = cartService.getActiveCartByCustomerId(customerId);

    LOG.info("entity:{}", entity);
    return cartApplication.getFullCart(entity);
  }

  /**
   * get cart by customer id.
   *
   * @param anonymousId the customer id
   * @return the cart by customer id
   */
  @ApiOperation(value = "get cart by anonymous id")
  @GetMapping(value = Router.CARTS_ROOT, params = "anonymousId")
  public CartView getCartByAnonymousId(
      @ApiParam(value = "anonymousId") @RequestParam String anonymousId) {
    LOG.info("anonymousId:{}", anonymousId);

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
  public CartView updateCart(@ApiParam(required = true) @PathVariable(Router.CART_ID) String id,
                             @RequestBody UpdateRequest updateRequest) {
    LOG.info("id:{}, updateRequest: {}", id, updateRequest);

    CartView result = cartApplication.updateCart(id, updateRequest.getVersion(), updateRequest
        .getActions());

    LOG.info("result:{}", result);
    return result;
  }

}
