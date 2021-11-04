package com.algotrader.starkex.types;

public class Order {
    private final String positionId;
    private final String humanSize;
    private final String limitFee; // Max fee fraction, e.g. 0.01 is a max 1% fee.
    private final DydxMarket market;
    private final StarkwareOrderSide side;
    private final String expirationIsoTimestamp;

    public Order(String positionId, String humanSize, String limitFee, DydxMarket market, StarkwareOrderSide side, String expirationIsoTimestamp) {
        this.positionId = positionId;
        this.humanSize = humanSize;
        this.limitFee = limitFee;
        this.market = market;
        this.side = side;
        this.expirationIsoTimestamp = expirationIsoTimestamp;
    }

    public String positionId() {
        return positionId;
    }

    public String humanSize() {
        return humanSize;
    }

    public String limitFee() {
        return limitFee;
    }

    public DydxMarket market() {
        return market;
    }

    public StarkwareOrderSide side() {
        return side;
    }

    public String expirationIsoTimestamp() {
        return expirationIsoTimestamp;
    }
}
