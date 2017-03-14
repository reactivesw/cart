package io.reactivesw.cart.application.model;

import io.reactivesw.model.LocalizedString;

import lombok.Data;

import java.util.List;

/**
 * Created by umasuo on 16/11/17.
 */
@Data
public class AssetView {
  private String id;

  private List<AssetSourceView> sources;

  private LocalizedString name;

  private LocalizedString description;

  private List<String> tags;
}
