package io.reactivesw.cart.application.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by umasuo on 17/2/21.
 */
@RestController
@Configuration
public class IndexController {
  @ApiOperation("/")
  @GetMapping("/")
  public String index() {
    return "Cart service, system time: " + System.currentTimeMillis();
  }
}
