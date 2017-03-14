package io.reactivesw.cart.application.model;

import io.reactivesw.model.Reference;

import lombok.Data;

/**
 * Created by umasuo on 16/11/17.
 */
@Data
public class LineItemDraft {

  private String productId;

  private Integer variantId;

  private Integer quantity;

  private Reference supplyChannel;

  private Reference distributionChannel;

}
