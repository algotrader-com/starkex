package com.algotrader.starkex.types;

public class Order {

    private final String positionId;
    private final String humanSize;
    private final String limitFee; // Max fee fraction, e.g. 0.01 is a max 1% fee.
    private final DydxAsset asset;
    private final StarkwareOrderSide side;
    private final String expirationIsoTimestamp;

    public Order(
            String positionId,
            String humanSize,
            String limitFee,
            DydxAsset asset,
            StarkwareOrderSide side,
            String expirationIsoTimestamp) {
        this.positionId = positionId;
        this.humanSize = humanSize;
        this.limitFee = limitFee;
        this.asset = asset;
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

    public DydxAsset asset() {
        return asset;
    }

    public StarkwareOrderSide side() {
        return side;
    }

    public String expirationIsoTimestamp() {
        return expirationIsoTimestamp;
    }

    @Override
    public String toString() {
        return "Order{" +
                "positionId='" + positionId + '\'' +
                ", humanSize='" + humanSize + '\'' +
                ", limitFee='" + limitFee + '\'' +
                ", asset=" + asset +
                ", side=" + side +
                ", expirationIsoTimestamp='" + expirationIsoTimestamp + '\'' +
                '}';
    }

}