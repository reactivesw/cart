package io.reactivesw.cart.infrastructure.update;


import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * update request.
 */
public class UpdateRequest {
  /**
   * The expected version of the category on which the changes should be applied.
   * If the expected version does not match the actual version, a 409 Conflict will be returned.
   */
  @NotNull
  @Min(0)
  private Integer version;

  /**
   * Array of UpdateAction.
   * The list of update action to be performed on the category.
   * Required.
   */
  @NotNull
  @Valid
  private List<UpdateAction> actions;

  /**
   * convert to UpdateActions.
   *
   * @return list of UpdateAction
   */
  public List<UpdateAction> getActions() {
    return actions.parallelStream().map(action -> (UpdateAction) action).collect(Collectors
        .toList());
  }

  /**
   * get version.
   *
   * @return
   */
  public Integer getVersion() {
    return version;
  }

  /**
   * set version
   *
   * @param version
   */
  public void setVersion(Integer version) {
    this.version = version;
  }

  /**
   * set actions.
   *
   * @param actions
   */
  public void setActions(List<UpdateAction> actions) {
    this.actions = actions;
  }
}
