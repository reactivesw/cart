package io.reactivesw.cart.application.model.event;

/**
 * Sign in event.
 */
public class SignInEvent {

  /**
   * customer id.
   */
  private String customerId;

  /**
   * anonymous id.
   */
  private String anonymousId;


  /**
   * default constructor.
   */
  public SignInEvent() {
    // do nothing.
  }

  /**
   * constructor.
   *
   * @param customerId  String
   * @param anonymousId String
   */
  public SignInEvent(String customerId, String anonymousId) {
    this.customerId = customerId;
    this.anonymousId = anonymousId;
  }

  /**
   * get customer Id.
   *
   * @return String
   */
  public String getCustomerId() {
    return customerId;
  }

  /**
   * set customer id.
   *
   * @param customerId String
   */
  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  /**
   * get anonymous id.
   *
   * @return String
   */
  public String getAnonymousId() {
    return anonymousId;
  }

  /**
   * set anonymous id.
   *
   * @param anonymousId String
   */
  public void setAnonymousId(String anonymousId) {
    this.anonymousId = anonymousId;
  }
}
