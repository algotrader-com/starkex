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


    public StarkwareOrderType getOrderType() {
        return orderType;
    }


    public BigInteger getQuantumsAmountFee() {
        return quantumsAmountFee;
    }


    public BigInteger getAssetIdFee() {
        return assetIdFee;
    }


    public String getPositionId() {
        return positionId;
    }


    public BigInteger getNonce() {
        return nonce;
    }

    public Integer getExpirationEpochHours() {
        return expirationEpochHours;
    }


    public StarkwareAmounts getStarkwareAmounts() {
        return starkwareAmounts;
    }
}
