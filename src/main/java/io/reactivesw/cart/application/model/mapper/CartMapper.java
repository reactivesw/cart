package io.reactivesw.cart.application.model.mapper;

import io.reactivesw.cart.application.model.CartView;
import io.reactivesw.cart.domain.model.Cart;

/**
 * cart mapper.
 */
public final class CartMapper {
  /**
   * default private constructor.
   */
  private CartMapper() {
  }

  /**
   * convert entity to model.
   * only map the basic info: id, version,time, subjectId, anonymousId, state, taxMode, country
   *
   * @param entity CartEntity
   * @return Cart
   */
  public static CartView entityToModel(Cart entity) {
    CartView cart = null;
    if (entity != null) {
      cart = new CartView();
      cart.setId(entity.getId());
      cart.setVersion(entity.getVersion());
      cart.setCreatedAt(entity.getCreatedAt());
      cart.setLastModifiedAt(entity.getLastModifiedAt());
      cart.setCustomerId(entity.getCustomerId());
      cart.setAnonymousId(entity.getAnonymousId());
      cart.setCartStatus(entity.getCartStatus());
    }
    return cart;
  }


}
