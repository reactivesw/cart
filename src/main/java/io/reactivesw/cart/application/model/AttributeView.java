package io.reactivesw.cart.application.model;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by umasuo on 16/11/17.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttributeView {

  private String name;

  private JsonNode value;
}
