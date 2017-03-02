package io.reactivesw.cart.infrastructure.auth;

import io.reactivesw.authentication.AuthPolicyProvider;
import io.reactivesw.authentication.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by umasuo on 17/3/2.
 */
@Component
public class CartAuthProvider implements AuthPolicyProvider {

  @Override
  public void checkScope(List<Scope> list) {

  }

  @Override
  public void checkBlackList(String s) {
    //do nothing
  }
}
