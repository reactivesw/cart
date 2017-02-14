package io.reactivesw.cart.application.model;

import io.reactivesw.model.LocalizedString;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by umasuo on 16/11/17.
 */
@ApiModel(description = "An Asset can be used to represent media assets, such as images, videos " +
    "or PDFs.\n Please find more information about use of Assets in the respective tutorial.\n")
@Data
public class Asset {
  @ApiModelProperty(required = true)
  private String id;

  @ApiModelProperty(value = "Array of AssetSource - Has at least one entry", required = true)
  private List<AssetSource> sources;

  @ApiModelProperty(value = " Localized String", required = true)
  private LocalizedString name;

  @ApiModelProperty(value = "Localized String", required = false)

  private LocalizedString description;

  @ApiModelProperty(required = false)
  private List<String> tags;

}
