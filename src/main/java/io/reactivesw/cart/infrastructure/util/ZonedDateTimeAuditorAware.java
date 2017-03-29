package io.reactivesw.cart.infrastructure.util;

import org.springframework.data.domain.AuditorAware;

/**
 * for zoned date time.
 */
public class ZonedDateTimeAuditorAware implements AuditorAware<String> {

  /**
   * return null auditor.
   *
   * @return String
   */
  @Override
  public String getCurrentAuditor() {
    return null;
  }
}