package io.reactivesw.cart.application.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * image view.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImageView {
  /**
   * URL of the image in its original size.
   * This can be used to obtain the image in different sizes.
   */
  private String url;
}
