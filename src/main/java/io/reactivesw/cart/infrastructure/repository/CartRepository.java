package io.reactivesw.cart.infrastructure.repository;

import io.reactivesw.cart.domain.model.Cart;
import io.reactivesw.cart.infrastructure.enums.CartState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by umasuo on 16/11/29.
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
  List<Cart> findByCustomerIdAndCartState(String customerId, CartState cartState);

  /**
   * find the active cart by anonymous id.
   *
   * @param anonymousId String
   * @return cart
   */
  Cart findOneByAnonymousId(String anonymousId);

}
