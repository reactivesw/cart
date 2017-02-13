package io.reactivesw.cart.domain.model;

import io.reactivesw.cart.infrastructure.enums.CartState;
import io.reactivesw.cart.infrastructure.enums.TaxMode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.ZonedDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * cart entity.
 * Created by umasuo on 16/11/25.
 */
@Entity
@Table(name = "order_cart")
@Data
@EqualsAndHashCode(callSuper = false)
public class Cart {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "id")
  protected String id;

  /**
   * The Created at.
   */
  @CreatedDate
  @Column(name = "created_at")
  protected ZonedDateTime createdAt;

  /**
   * The Last modified at.
   */
  @LastModifiedDate
  @Column(name = "last_modified_at")
  protected ZonedDateTime lastModifiedAt;

  /**
   * version.
   */
  @Column
  @Version
  private Integer version;

  /**
   * customer id.
   */
  @Column(name = "customer_id")
  private String customerId;

  /**
   * anonymous id. each anonymous id only has one cart.
   */
  @Column(name = "anonymous_id", unique = true)
  private String anonymousId;

  /**
   * List of line items.
   */
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Set<LineItemValue> lineItems;

  /**
   * cart status.
   */
  @Column
  private CartState cartState;

  /**
   * the shipping address.
   */
  @Column(name = "shipping_address_id")
  private String shippingAddress;

  /**
   * the billing address.
   */
  @Column(name = "billing_address_id")
  private String billingAddress;

  /**
   * tax mode.
   */
  @Column(name = "tax_mode")
  private TaxMode taxMode;

  /**
   * A two-digit country code as per ↗ ISO 3166-1 alpha-2 . Used for product variant price
   * selection.
   */
  @Column(name = "country")
  private String country;

  /**
   * the currency code for this cart.
   */
  @Column(name = "currency_code")
  private String currencyCode;

  /**
   * Set automatically once the ShippingMethod is set.
   */
  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private ShippingInfoValue shippingInfo;

}
