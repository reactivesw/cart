package io.reactivesw.cart.infrastructure.repository;

import io.reactivesw.cart.domain.model.Cart;
import io.reactivesw.cart.infrastructure.enums.CartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * cart repository.
 */
@Repository
public interface CartRepository extends JpaRepository<Cart, String>,
    CrudRepository<Cart, String> {

  /**
   * find by customer id.
   *
   * @param customerId String
   * @return list of cart, but can contains one at most.
   */
  List<Cart> findByCustomerId(String customerId);

  /**
   * find the active cart by customer id.
   *
   * @param customerId String
   * @return list of cart, but can contains one at most.
   */
  List<Cart> findByCustomerIdAndCartState(String customerId, CartStatus cartState);

  /**
   * find the active cart by anonymous id.
   *
   * @param anonymousId String
   * @return cart
   */
  Cart findOneByAnonymousId(String anonymousId);

}
