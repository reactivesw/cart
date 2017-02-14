package io.reactivesw.cart.application.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Created by Davis on 16/11/17.
 */
@Data
public class TaxCategoryView {

  /**
   * The unique ID of the category.
   */
  private String id;

  /**
   * The current version of the category.
   */
  private Integer version;

  /**
   * The Created at.
   */
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
  private ZonedDateTime createdAt;

  /**
   * The Last modified at.
   */
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
  private ZonedDateTime lastModifiedAt;

  /**
   * The Name.
   */
  private String name;

  /**
   * The Description.
   */
  private String description;

  /**
   * The tax rates have unique IDs in the rates list
   */
  private List<TaxRateView> rates;

}
