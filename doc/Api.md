### 1 Introduction
Cart service.

### 2 Model
#### 2.1 CartView
| field name        | field type        | comments |
|----|----|----|
| id                | String            | |
| createdAt         | ZonedDateTime     | |
| lastModifiedAt    | ZonedDateTime     | |
| customerId        | String            | | 
| anonymousId       | String            | |
| lineItems         | List<LineItemView>| |
| totalPrice        | Money             | |
| cartState         | CartState         | |

#### 2.2 LineItemView
| field name        | field type        | comments |
|----|----|----|
| id                | String            | |
| productId         | String            | |
| name              | LocalizedString   | |
| variantId         | Integer           | | 
| sku               | String            | |
| images            | List<ImageView>   | |
| price             | Money             | |
| totalPrice        | Money             | |
| quantity          | Integer           | |


#### 2.3 ImageView
| field name        | field type        | comments |
|----|----|----|
| url               | String            | |
| label             | String            | |

### 3 Api
#### 3.1 get cart by cartId
- Path: /{cartId}
- Payload: cartId -- NotNull
- Response: CartView

#### 3.2 get cart by customerId
- Path: /
- Payload: customerId -- NotNull
- Response: CartView
- Sample: {RootUrl}?customerId=ERTAYDASD-ADAVFCA-SADSDASDA-SCAS

#### 3.3 get cart by anonymousId
- Path: /
- Payload: anonymousId -- NotNull
- Response: CartView
- Sample: {RootUrl}?anonymousId=ERTAYDASD-ADAVFCA-SADSDASDA-SCAS

#### 3.4 update cart
- Path: /{cartId}
- Payload: cartId --NotNull, UpdateRequest --NotNull
- Response: CartView

### 4 Actions
Actions for update cart
#### 4.1 AddLineItem
```Java

  @NotNull
  private String productId;

  @NotNull
  private Integer variantId;

  @NotNull
  @Min(1)
  private Integer quantity;
```
#### 4.2 RemoveLineItem
```Java

  @NotNull
  private String lineItemId;

  private Integer quantity;
```
#### 4.3 SetLineItemQuantity
```Java
  @NotNull
  private String lineItemId;

  @NotNull
  @Min(1)
  private Integer quantity;
```
#### 4.4 SetCustomerId
```Java
  @NotNull
  private String customerId;
```