package io.reactivesw.cart.application.model.mapper;

import io.reactivesw.cart.application.model.LineItemDraft;
import io.reactivesw.cart.application.model.LineItemView;
import io.reactivesw.cart.application.model.ProductView;
import io.reactivesw.cart.domain.model.LineItem;

/**
 * Created by umasuo on 16/12/5.
 */
public class LineItemMapper {

  /**
   * convert model to entity.
   *
   * @param model LineItemDraft
   * @return LineItemValue
   */
  public static LineItem modelToEntity(LineItemDraft model) {
    LineItem entity = null;
    if (model != null) {
      entity = new LineItem();
      entity.setProductId(model.getProductId());

      entity.setQuantity(model.getQuantity());

      entity.setVariant(model.getVariantId());

      String supplyChannel = model.getSupplyChannel() == null ? null : model.getSupplyChannel()
          .getId();
      entity.setSupplyChannel(supplyChannel);

      String distributionChannel = model.getDistributionChannel() == null ? null : model
          .getDistributionChannel().getId();
      entity.setDistributionChannel(distributionChannel);
    }
    return entity;
  }

  /**
   * fill view with product view.
   * @param itemView
   * @param productView
   */
  public static void fillView(LineItemView itemView, ProductView productView) {
    itemView.setName(productView.getName());
    itemView.setPrice(productView.getVariant().getPrice());
    itemView.setProductVariant(productView.getVariant());
    itemView.setSlug(productView.getSlug());
  }

}
