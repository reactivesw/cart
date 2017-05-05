# 1 Cart requirements for customer
This doc mainly describes the requirements for customer.

# Requirements
This part describes the detail requirements.

**Key futures**

- Customer can get anonymous cart without login
- Customer's anonymous cart should be merged to his/her cart when he/she login
- Customer's cart should be auto created when he/she try to get his/her cart and it dose not exists
- Each customer only has one active cart at the same time
- When a cart be checked out, then it can not be changed anymore, and it's status should change to `ordered`


## Get cart for anonymous customer
Before the customer login, he/she is in anonymous status, and can get an anonymous cart from the cart system. And if there is no cart that bind to this anonymous id, then create a new one. 

## Get cart for login customer
After the customer login, then he/she can get his/her cart. If the cart not exist, then create it, also if he/she has anonymous cart that are not merged before, then merge it.

## Update cart
Customer can add product or remove product to cart at any time.

## Checkout cart
Customer can checkout a cart. When a cart be checked out, then it can not be changed anymore, and it's status should change to `ordered`, and if the customer get his/her cart again, it should be a new active cart. 

