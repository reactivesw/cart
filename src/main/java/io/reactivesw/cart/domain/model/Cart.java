package io.reactivesw.cart.domain.model;

import io.reactivesw.cart.infrastructure.enums.CartStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * cart entity.
 */
@Entity
@Table(name = "cart")
@Data
@EqualsAndHashCode(callSuper = false)
@EntityListeners(AuditingEntityListener.class)
public class Cart {

  /**
   * uuid.
   */
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "id")
  protected String id;

  /**
   * The Created at.
   */
  @CreatedDate
  @Column
  protected ZonedDateTime createdAt;

  /**
   * The Last modified at.
   */
  @LastModifiedDate
  @Column
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
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<LineItem> lineItems;

  /**
   * cart status.
   */
  @Column
  private CartStatus cartState;

}
