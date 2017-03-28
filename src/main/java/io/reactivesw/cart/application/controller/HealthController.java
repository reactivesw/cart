package io.reactivesw.cart.application.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static io.reactivesw.cart.infrastructure.Router.CART_HEALTH_CHECK;

/**
 * health api.
 */
@RestController
@Configuration
public class HealthController {

  /**
   * service name.
   */
  @Value("${spring.application.name}")
  private transient String serviceName;

  /**
   * this api is used for health check.
   *
   * @return service name.
   */
  @GetMapping(CART_HEALTH_CHECK)
  public String health() {
    return serviceName + ", system time: " + System.currentTimeMillis();
  }
}
