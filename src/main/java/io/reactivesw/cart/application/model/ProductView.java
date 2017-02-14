package io.reactivesw.cart.application.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.reactivesw.model.LocalizedString;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by Davis on 16/11/17.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductView {
  /**
   * the name.
   */
  private LocalizedString name;

  /**
   * the description.
   */
  private LocalizedString description;

  /**
   * human-readable identifiers usually used as deep-link URL to the related product.
   * Each slug is unique across a merchant,
   * but a product can have the same slug for different languages.
   */
  private String slug;

  /**
   * the meta title.
   * optional.
   */
  private LocalizedString metaTitle;

  /**
   * The Meta description.
   */
  private LocalizedString metaDescription;

  /**
   * The Meta keywords.
   */
  private LocalizedString metaKeywords;

  /**
   * masterVariant.
   */
  private ProductVariantView variant;

}
