package io.reactivesw.cart.application.model;

import io.reactivesw.model.LocalizedString;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by umasuo on 16/11/17.
 */
@ApiModel(description = "An AssetView can be used to represent media assets, such as images, videos " +
    "or PDFs.\n Please find more information about use of Assets in the respective tutorial.\n")
@Data
public class AssetView {
  @ApiModelProperty(required = true)
  private String id;

  @ApiModelProperty(value = "Array of AssetSourceView - Has at least one entry", required = true)
  private List<AssetSourceView> sources;

  @ApiModelProperty(value = " Localized String", required = true)
  private LocalizedString name;

  @ApiModelProperty(value = "Localized String", required = false)

  private LocalizedString description;

  @ApiModelProperty(required = false)
  private List<String> tags;

}
