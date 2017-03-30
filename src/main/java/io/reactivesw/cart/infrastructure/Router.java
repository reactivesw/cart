package io.reactivesw.cart.infrastructure;

/**
 * Created by umasuo on 16/12/20.
 */
public final class Router {
  /**
   * root url of cart.
   */
  public static final String CARTS_ROOT = "/";

  /**
   * cart id.
   */
  public static final String CART_ID = "cartId";

  /**
   * id pattern.
   */
  public static final String ID_PATTERN = "{" + CART_ID + "}";

  /**
   * get cart by id.
   */
  public static final String CART_WITH_ID = CARTS_ROOT + ID_PATTERN;

  /**
   * checkout.
   */
  public static final String CART_CHECKOUT = CARTS_ROOT + ID_PATTERN + "/checkout";


  /**
   * The constant cart_health_check.
   */
  public static final String CART_HEALTH_CHECK = CARTS_ROOT + "health";

  /**
   * private constructor.
   */
  private Router() {
    super();
  }

  /**
   * path builder: get cart with id.
   *
   * @param id cart id
   * @return path cart with id
   */
  public static String getCartWithId(String id) {
    return CARTS_ROOT + id;
  }
}
