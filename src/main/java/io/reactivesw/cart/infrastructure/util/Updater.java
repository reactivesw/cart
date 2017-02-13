package io.reactivesw.cart.infrastructure.util;

import com.google.common.collect.ImmutableMap;
import io.reactivesw.cart.domain.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * we may got two kind of update: just use the data in action, or still use data from other service.
 * if we just use the data in action, we can only use action mapper to set the data.
 * if we need get data from other palace, we should use update service.
 * Created by umasuo on 16/12/7.
 */
@Service
public class Updater {

  /**
   * ImmutableMap for discount code update mapper.
   */
  Map<Class<?>, Updater> updateMappers = ImmutableMap.of(
//      SetCustomerPaymentId.class, new SetCustomerPaymentIdMapper()
  );

  /**
   * ApplicationContext for get update services.
   */
  @Autowired
  private transient ApplicationContext context;

  /**
   * put the value in action to entity.
   *
   * @param entity E
   * @param action UpdateAction
   */
  public void handle(Cart entity, UpdateAction action) {
    Updater updater = getUpdateService(action.getClass());
    updater.handle(entity, action);
  }

  /**
   * get mapper.
   *
   * @param clazz UpdateAction class
   * @return ZoneUpdateMapper
   */
  private Updater getUpdateService(Class<?> clazz) {
    Updater updater = updateMappers.get(clazz);
    if (updater == null) {
      updater = (Updater) context.getBean(clazz);
    }
    return updater;
  }

}
