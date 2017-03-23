package io.reactivesw.cart.application.model;

import io.reactivesw.model.LocalizedString;
import io.reactivesw.model.Money;
import lombok.Data;

import java.util.List;

/**
 * Created by umasuo on 16/11/17.
 */
@Data
public class LineItemView {

  private String id;

  private String productId;

  private LocalizedString name;

  private Integer variantId;

  private String sku;

  private List<ImageView> images;

  private Money price;

  private Money totalPrice;

  private Integer quantity;

}
