package io.reactivesw.cart.application.model;

import io.reactivesw.model.LocalizedString;
import io.reactivesw.model.Money;
import lombok.Data;

import java.util.List;

/**
 * line item view.
 */
@Data
public class LineItemView {

  /**
   * id.
   */
  private String id;

  /**
   * product id.
   */
  private String productId;

  /**
   * name.
   */
  private LocalizedString name;

  /**
   * variant id.
   */
  private Integer variantId;

  /**
   * sku.
   */
  private String sku;

  /**
   * list of imageViews.
   */
  private List<ImageView> images;

  /**
   * price.
   */
  private Money price;

  /**
   * total price.
   */
  private Money totalPrice;

  /**
   * quantity.
   */
  private Integer quantity;

}
