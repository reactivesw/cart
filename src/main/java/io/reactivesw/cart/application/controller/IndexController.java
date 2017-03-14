package io.reactivesw.cart.application.controller;

import static io.reactivesw.cart.infrastructure.Router.CART_HEALTH_CHECK;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by umasuo on 17/2/21.
 */
@RestController
@Configuration
public class IndexController {

  /**
   * service name.
   */
  @Value("${spring.application.name}")
  private String serviceName;

  /**
   * this api is used for health check.
   *
   * @return service name.
   */
  @GetMapping(CART_HEALTH_CHECK)
  public String index() {
    return serviceName + ", system time: " + System.currentTimeMillis();
  }
}
