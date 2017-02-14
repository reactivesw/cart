package io.reactivesw.cart.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by umasuo on 16/11/28.
 */
@Entity
@Table(name = "shipping_info")
@Data
@EqualsAndHashCode(callSuper = false)
public class ShippingInfo {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "id")
  protected String id;

  /**
   * shipping method.
   */
  @Column(name = "shipping_method_id")
  private String shippingMethod;

}
