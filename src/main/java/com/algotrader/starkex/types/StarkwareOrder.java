package com.algotrader.starkex.types;

import java.math.BigInteger;


public class StarkwareOrder {
    private final StarkwareAmounts starkwareAmounts;
    private final StarkwareOrderType orderType;
    private final BigInteger quantumsAmountFee;
    private final BigInteger assetIdFee;
    private final String positionId;
    private final BigInteger nonce; // For signature. A base-10 integer.
    private final Integer expirationEpochHours;

    public StarkwareOrder(StarkwareAmounts starkwareAmounts, StarkwareOrderType orderType, BigInteger quantumsAmountFee, BigInteger assetIdFee, String positionId, BigInteger nonce, Integer expirationEpochHours) {
        this.starkwareAmounts = starkwareAmounts;
        this.orderType = orderType;
        this.quantumsAmountFee = quantumsAmountFee;
        this.assetIdFee = assetIdFee;
        this.positionId = positionId;
        this.nonce = nonce;
        this.expirationEpochHours = expirationEpochHours;
    }


    public StarkwareOrderType orderType() {
        return orderType;
    }


    public BigInteger quantumsAmountFee() {
        return quantumsAmountFee;
    }


    public BigInteger assetIdFee() {
        return assetIdFee;
    }


    public String positionId() {
        return positionId;
    }


    public BigInteger nonce() {
        return nonce;
    }

    public Integer expirationEpochHours() {
        return expirationEpochHours;
    }


    public StarkwareAmounts starkwareAmounts() {
        return starkwareAmounts;
    }
}
