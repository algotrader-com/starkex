package com.algotrader.starkex.types;

import java.math.BigInteger;

public final class StarkwareAmounts {
    private final BigInteger quantumsAmountSynthetic;
    private final BigInteger quantumsAmountCollateral;
    private final BigInteger assetIdSynthetic;
    private final BigInteger assetIdCollateral;
    private final boolean isBuyingSynthetic;

    public StarkwareAmounts(BigInteger quantumsAmountSynthetic, BigInteger quantumsAmountCollateral, BigInteger assetIdSynthetic, BigInteger assetIdCollateral, boolean isBuyingSynthetic) {
        this.quantumsAmountSynthetic = quantumsAmountSynthetic;
        this.quantumsAmountCollateral = quantumsAmountCollateral;
        this.assetIdSynthetic = assetIdSynthetic;
        this.assetIdCollateral = assetIdCollateral;
        this.isBuyingSynthetic = isBuyingSynthetic;
    }

    public BigInteger quantumsAmountSynthetic() {
        return quantumsAmountSynthetic;
    }


    public BigInteger quantumsAmountCollateral() {
        return quantumsAmountCollateral;
    }


    public BigInteger assetIdSynthetic() {
        return assetIdSynthetic;
    }


    public BigInteger assetIdCollateral() {
        return assetIdCollateral;
    }


    public boolean isBuyingSynthetic() {
        return isBuyingSynthetic;
    }

}
