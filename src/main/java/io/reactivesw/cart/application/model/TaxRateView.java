package io.reactivesw.cart.application.model;

import lombok.Data;

import java.util.List;

/**
 * Created by umasuo on 16/11/17.
 */
@Data
public class TaxRateView {

  private String id;

  private String name;

  private Float amount;

  private Boolean includedInPrice;

  private String country;

  private String state;

  private List<SubRateView> subRates;
}
