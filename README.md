# dYdX STARK Key Elliptic Curve Signature 

For usage with the [dYdX REST API](https://docs.dydx.exchange/)

## Intro
Within the dYdX L2 system, authentication is handled by a separate key, known as the account's STARK key.

STARK key authentication is required for the following operations:

* Place an order
* Withdraw funds

For futher details see:
https://docs.dydx.exchange/#authentication

## Installation

```
mvn install
```

## Usage:

```
OrderWithClientIdWithPrice order = new OrderWithClientIdWithPrice(
        new Order("56277",
                "1",
                "0.001",
                new DydxAsset("ATOM", 7),
                StarkwareOrderSide.BUY,
                "2021-09-20T00:00:00.000Z"),
        "123456",
        "34.00"
);
StarkSigner starkSigner = new StarkSigner();
Signature signature = starkSigner.sign(order, NetworkId.ROPSTEN, PRIVATE_KEY);
```
