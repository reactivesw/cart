package io.reactivesw.cart.application.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Id;

/**
 * Created by umasuo on 17/2/13.
 */
@Data
public class AddressView {

  @JsonProperty
  @Id
  private String id;

  @JsonProperty
  private String title;

  @JsonProperty
  private String salutation;

  @JsonProperty
  private String firstName;

  @JsonProperty
  private String lastName;

  @JsonProperty
  private String streetName;

  @JsonProperty
  private String streetNumber;

  @JsonProperty
  private String additionalStreetInfo;

  @JsonProperty
  private String postalCode;

  @JsonProperty
  private String city;

  @JsonProperty
  private String region;

  @JsonProperty
  private String state;

  @JsonProperty
  private String country;

  @JsonProperty
  private String company;

  @JsonProperty
  private String department;

  @JsonProperty
  private String building;

  @JsonProperty
  private String apartment;

  @JsonProperty
  private String phone;

  @JsonProperty
  private String mobile;

  @JsonProperty
  private String email;

  @JsonProperty
  private String fax;

  @JsonProperty
  private String additionalAddressInfo;

  @JsonProperty
  private String externalId;
}
