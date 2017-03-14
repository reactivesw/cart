package io.reactivesw.cart.application.model;

import lombok.Data;

/**
 * Created by umasuo on 16/11/17.
 */
@Data
public class AssetSourceView {

  private String uri;

  private String key;

  private AssetDimensionsView dimensions;

  private String contentType;
}
