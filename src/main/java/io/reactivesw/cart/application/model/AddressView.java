package io.reactivesw.cart.application.model;

import lombok.Data;

/**
 * Created by umasuo on 17/2/13.
 */
@Data
public class AddressView {

  /**
   * uuid.
   */
  private String id;

  /**
   * address name.
   */
  private String name;

  private String salutation;

  private String firstName;

  private String lastName;

  private String postalCode;

  private String phone;

  private String email;

  private String firstLine;

  private String secondLine;
}
