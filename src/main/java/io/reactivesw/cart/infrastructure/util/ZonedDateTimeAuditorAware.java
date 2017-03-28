package io.reactivesw.cart.infrastructure.util;

import org.springframework.data.domain.AuditorAware;

/**
 * Created by Davis on 17/3/7.
 */
public class ZonedDateTimeAuditorAware implements AuditorAware<String> {

  /**
   * return null auditor.
   *
   * @return
   */
  @Override
  public String getCurrentAuditor() {
    return null;
  }
}