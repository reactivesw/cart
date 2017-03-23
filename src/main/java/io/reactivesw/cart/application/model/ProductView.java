package io.reactivesw.cart.application.model;

import io.reactivesw.model.LocalizedString;
import lombok.Data;

import java.util.List;

/**
 * Created by umasuo on 17/3/23.
 */
@Data
public class ProductView {

  /**
   * product id.
   */
  private String productId;

  /**
   * name in localized string.
   */
  private LocalizedString name;

  /**
   * id in number.
   */
  private Integer variantId;

  /**
   * sku.
   */
  private String sku;

  /**
   * list of images.
   */
  private List<ImageView> images;

  /**
   * Price view.
   */
  private PriceView price;
}
