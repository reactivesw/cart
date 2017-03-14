package io.reactivesw.cart.application.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.reactivesw.model.Money;
import io.reactivesw.model.Reference;

import lombok.Data;

import java.time.ZonedDateTime;

/**
 * Created by umasuo on 16/11/17.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PriceView {

  private String id;

  private Money value;

  private String country;

  private Reference customerGroup;

  private Reference channel;

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
  private ZonedDateTime validFrom;

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
  private ZonedDateTime validUntil;

}
