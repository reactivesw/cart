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
@Table(name = "line_item")
@Data
@EqualsAndHashCode(callSuper = false)
public class LineItem {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "id")
  protected String id;

  /**
   * product id.
   */
  @Column(name = "product_id")
  private String productId;

  /**
   * product variant.
   * a snap shop for variant.
   */
  @Column(name = "variant_id")
  private Integer variant;

  /**
   * quantity.
   */
  @Column
  private Integer quantity;

  /**
   * supplyChannel.
   */
  @Column(name = "supply_channel")
  private String supplyChannel;

  /**
   * distributionChannel.
   */
  @Column(name = "distribution_channel")
  private String distributionChannel;

}
