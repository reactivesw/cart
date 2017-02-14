package io.reactivesw.cart.application.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * Created by umasuo on 16/11/17.
 */
@Data
@ApiModel
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImageView {
  /**
   * URL of the image in its original size.
   * This can be used to obtain the image in different sizes.
   */
  private String url;

  /**
   * Dimensions of the original image.
   * This can be used by your application e.g. to determine
   * whether the image is large enough to display a zoom view.
   */
  private AssetDimensionsView dimensions;

  /**
   * Custom label that can be used, for example, as an image description.
   */
  private String label;
}
