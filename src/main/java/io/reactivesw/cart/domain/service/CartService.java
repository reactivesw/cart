package io.reactivesw.cart.domain.service;

import io.reactivesw.cart.domain.model.Cart;
import io.reactivesw.cart.infrastructure.enums.CartStatus;
import io.reactivesw.cart.infrastructure.repository.CartRepository;
import io.reactivesw.cart.infrastructure.update.UpdateAction;
import io.reactivesw.cart.infrastructure.update.UpdaterService;
import io.reactivesw.exception.AlreadyExistException;
import io.reactivesw.exception.ConflictException;
import io.reactivesw.exception.ImmutableException;
import io.reactivesw.exception.NotExistException;
import io.reactivesw.exception.ParametersException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * Created by umasuo on 16/11/29.
 */
@Service
public class CartService {

  /**
   * logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(CartService.class);

  /**
   * cart repository.
   */
  @Autowired
  private transient CartRepository cartRepository;

  /**
   * cart update service.
   */
  @Autowired
  private transient UpdaterService cartUpdater;

  /**
   * Create an new active cart with sample.
   * Either customer id or anonymous id must be set.
   * Each customer(include anonymous customer) can only have one active cart.
   * If the customer already has an cart, then throw exception.
   *
   * @param cart CartEntity
   * @return CartEntity
   */
  public Cart createActiveCart(Cart cart) {
    String customerId = cart.getCustomerId();
    String anonymousId = cart.getAnonymousId();

    if (StringUtils.isBlank(customerId) && StringUtils.isBlank(anonymousId)) {
      throw new ParametersException("Either customer id or anonymous id must be set.");
    }

    List<Cart> result = null;
    if (StringUtils.isNotBlank(customerId)) {
      result = this.cartRepository.findByCustomerIdAndCartStatus(anonymousId,
          CartStatus.Active);
    } else if (StringUtils.isNotBlank(anonymousId)) {
      result = this.cartRepository.findByCustomerIdAndCartStatus(anonymousId,
          CartStatus.Active);
    }

    if (result != null && !result.isEmpty()) {
      throw new AlreadyExistException("The customer already has an active cart. ");
    }

    //TODO recalculate the cart
    return cartRepository.save(cart);
  }

  /**
   * get cart by cart Id.
   *
   * @param cartId String
   * @return CartEntity
   */
  public Cart getById(String cartId) {
    LOG.debug("CartId:{}", cartId);
    Cart entity = this.cartRepository.findOne(cartId);

    if (entity == null) {
      throw new NotExistException("CartView not exist with id: " + cartId);
    }

    return entity;
  }

  /**
   * get cart by cart Id.
   *
   * @param cartId String
   * @return CartEntity
   */
  public Cart checkout(String cartId) {
    LOG.debug("enter. cartId: {}", cartId);
    Cart cart = this.cartRepository.findOne(cartId);
    if (cart == null) {
      throw new NotExistException("CartView not exist with id: " + cartId);
    }
    if (cart.getCartStatus() != CartStatus.Active) {
      LOG.debug("Only active cart can be ordered, id: {}", cartId);
      throw new ImmutableException("Only active Cart can be ordered");
    }
    cart.setCartStatus(CartStatus.Ordered);
    this.cartRepository.save(cart);
    return cart;
  }

  /**
   * get cart by customer id and cart state.
   *
   * @param customerId String
   * @param state      CartStatus
   * @return CartEntity
   */
  public List<Cart> getCartByCustomerIdAndState(String customerId, CartStatus state) {
    return this.cartRepository.findByCustomerIdAndCartStatus(customerId, state);
  }

  /**
   * Get cart by customer id and cart state.
   * Each customer(include the anonymous customer) can only have one active cart.
   * If can not find one, then create it.
   *
   * @param customerId String
   * @return CartEntity
   */
  public Cart getActiveCartByCustomerId(String customerId) {
    LOG.debug("subjectId:{}", customerId);

    List<Cart> result = this.cartRepository.findByCustomerIdAndCartStatus(customerId,
        CartStatus.Active);
    Cart cart = result.parallelStream().findAny().orElse(null);

    if (cart == null) {
      cart = this.createActiveCartWithCustomerId(customerId);
    }

    return cart;
  }

  /**
   * Get cart by anonymous id and cart state.
   * Each customer(include the anonymous customer) can only have one active cart.
   * If can not find one, then create it.
   *
   * @param anonymousId String
   * @return CartEntity
   */
  public Cart getCartByAnonymousId(String anonymousId) {
    LOG.debug("anonymousId:{}", anonymousId);

    Cart result = this.cartRepository.findOneByAnonymousId(anonymousId);

    if (result == null) {
      result = this.createActiveCartWithAnonymousId(anonymousId);
    }

    return result;
  }

  /**
   * update Cart.
   *
   * @param id      String
   * @param version Integer
   * @param actions List of UpdateAction
   * @return Cart
   */
  public Cart updateCart(String id, Integer version, List<UpdateAction> actions) {
    Cart cart = this.getById(id);

    this.validateVersion(version, cart.getVersion());
    if (cart.getCartStatus() != CartStatus.Active) {
      LOG.debug("Only active CartView can be changed, id:{}", id);
      throw new ImmutableException("Only active CartView can be changed");
    }

    //update data from action
    actions.stream().forEach(
        action -> cartUpdater.handle(cart, action)
    );
    return this.cartRepository.save(cart);
  }

  /**
   * setter of the cart repository.
   *
   * @param cartRepository CartRepository
   */
  protected void setCartRepository(CartRepository cartRepository) {
    this.cartRepository = cartRepository;
  }

  /**
   * Create an new cart with customer id.
   * This function should only be called by system, not by customer.
   *
   * @param customerId String
   * @return CartEntity
   */
  private Cart createActiveCartWithCustomerId(String customerId) {
    Cart cart = new Cart();
    cart.setCustomerId(customerId);
    cart.setCartStatus(CartStatus.Active);
    Cart savedCart = cartRepository.save(cart);
    LOG.info("Create a new active cart with subjectId:{}, entity:{}", customerId, savedCart
        .toString());
    return savedCart;
  }

  /**
   * create an new cart with anonymous id.
   * This function should only be called by system, not by customer.
   *
   * @param anonymousId String
   * @return CartEntity
   */
  private Cart createActiveCartWithAnonymousId(String anonymousId) {
    LOG.debug("enter, anonymousId: {}", anonymousId);

    Cart cart = new Cart();
    cart.setAnonymousId(anonymousId);
    cart.setCartStatus(CartStatus.Active);
    Cart savedCart = cartRepository.save(cart);

    LOG.debug("Create a new active cart with anonymousId:{}, entity:{}", anonymousId, savedCart
        .toString());
    return savedCart;
  }

  /**
   * delete cart.
   *
   * @param id      String
   * @param version Integer
   */
  @Transactional
  public void deleteCart(String id, Integer version) {
    LOG.debug("enter, id: {}, version: {}", id, version);
    Cart cart = this.getById(id);

    this.validateVersion(version, cart.getVersion());

    if (cart.getCartStatus() != CartStatus.Active) {
      LOG.debug("Only active CartView can be changed, id:{}", id);
      throw new ImmutableException("Only active CartView can be changed");
    }

    this.cartRepository.delete(id);
  }

  /**
   * check the version of the discount.
   *
   * @param inputVersion Integer
   * @param savedVersion Integer
   */
  private void validateVersion(Integer inputVersion, Integer savedVersion) {
    if (!Objects.equals(inputVersion, savedVersion)) {
      LOG.debug("CartView version is not correct. inputVersion:{}, savedVersion:{}",
          inputVersion, savedVersion);
      throw new ConflictException("CartView version is not correct.");
    }
  }
}
