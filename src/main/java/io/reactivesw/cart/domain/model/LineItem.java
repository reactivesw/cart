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
 * line item entity.
 */
@Entity
@Table(name = "line_item")
@Data
@EqualsAndHashCode(callSuper = false)
public class LineItem {

  /**
   * uuid.
   */
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "id")
  private String id;

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
  private Integer variantId;

  /**
   * quantity.
   */
  @Column
  private Integer quantity;

}
