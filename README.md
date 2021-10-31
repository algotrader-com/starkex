# StarkWare Elliptic Curve Signature 

For usage with the [dYdX REST API](https://docs.dydx.exchange/)

## Intro
Within the dYdX L2 system, authentication is handled by a separate key pair, known as the account's STARK key pair.

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
OrderWithClientIdAndQuoteAmount order = new OrderWithClientIdAndQuoteAmount(
        new Order("56277",
                "1",
                "0.001",
                DydxMarket.ATOM_USD,
                StarkwareOrderSide.BUY,
                "2021-09-20T00:00:00.000Z"),
        "123456",
        "34.00"
);
StarkSigner starkSigner = new StarkSigner();
Signature signature = starkSigner.sign(order, NetworkId.ROPSTEN, PRIVATE_KEY);
```
