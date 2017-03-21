package io.reactivesw.cart.infrastructure.update;

import com.google.common.collect.ImmutableMap;
import io.reactivesw.cart.domain.model.Cart;
import io.reactivesw.model.Updater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * we may got two kind of update: just use the data in action, or still use data from other service.
 * if we just use the data in action, we can only use action mapper to set the data.
 * if we need get data from other palace, we should use update service.
 * Created by umasuo on 16/12/7.
 */
@Service
public class UpdaterService implements Updater<Cart, UpdateAction> {

  /**
   * ImmutableMap for discount code update mapper.
   */
  Map<Class<?>, Updater> updateMappers = new ConcurrentHashMap<>();

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
  @Override
  public void handle(Cart entity, UpdateAction action) {
    Updater updater = getUpdateService(action);
    updater.handle(entity, action);
  }

  /**
   * get mapper.
   *
   * @param action UpdateAction
   * @return ZoneUpdateMapper
   */
  private Updater getUpdateService(UpdateAction action) {
    Updater updater = updateMappers.get(action.getActionName());
    if (updater == null) {
      updater = (Updater) context.getBean(action.getActionName());
    }
    return updater;
  }

}
