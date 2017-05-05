# Cart design for customer
This doc mainly describes the design of cart service for customer.

# 1 Basic features
**Key futures**

- Customer can get anonymous cart without login
- Customer's anonymous cart should be merged to his/her cart when he/she login
- Customer's cart should be auto created when he/she try to get his/her cart and it dose not exists
- Each customer only has one active cart at the same time
- When a cart be checked out, then it can not be changed anymore, and it's status should change to `ordered`

# 2 Model
See [api doc](./api.md)

# 3 Workflow
## 3.1 Get cart for anonymous customer
- Get cart by anonymous id
- If the cart not exists, then create a new one
- Convert the cart to cartView
- Fill the cartView with data fetch from product service
- Return cartView

## 3.2 Get cart for login customer
- Get cart by customer id
- If the cart not exits, then create a new one
- Convert the cart to cartView
- Fill the cartView with data fetch from product service
- Return cartView

## 3.3 Update cart
- Get cart by cart id
- Update cart with actions
- Save cart to database
- Convert the cart to cartView
- Fill the cartView with data fetch from product service
- Return cartView


## 3.4 Checkout cart
- Get cart by cart id
- Change cart's status to `ordered`
- Convert the cart to cartView
- Fill the cartView with data fetch from product service
- Return cartView

## 3.5 Merge cart
- Get cart by anonymous id
- Get cart by customer id
- merge anonymous cart to customer cart and change anonymous cart's status to `merged`
- save two cart

# 4 Event
Cart serves consumes event:`customer login with anonymous id`

## 4.1 Event: customer login with anonymous id
When customer login with anonymous id, then the cart service should merge the cart that belong to the anonymous id to the cart of this customer.
### 4.1.1 Event body
```java

  /**
   * customer id.
   */
  private String customerId;

  /**
   * anonymous id.
   */
  private String anonymousId;
```
### 4.1.2 Consume process
- Get event from event bus
- Merge cart with event data, also see `3.5`
