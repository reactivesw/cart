package io.reactivesw.cart.infrastructure.util;

import io.reactivesw.cart.application.model.LineItemView;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Compare address with create time.
 */
public class CreateTimeComparator implements Comparator<LineItemView>, Serializable {

  /**
   * Auto generated serial version id.
   */
  private static final long serialVersionUID = -79751054479830991L;

  /**
   * Compare two address with create time.
   */
  @Override
  public int compare(LineItemView o1, LineItemView o2) {
    int result = 0;
    if (o1.getCreatedAt() != null && o2.getCreatedAt() != null) {
      result = o1.getCreatedAt().compareTo(o2.getCreatedAt());
    }
    return result;
  }
}
