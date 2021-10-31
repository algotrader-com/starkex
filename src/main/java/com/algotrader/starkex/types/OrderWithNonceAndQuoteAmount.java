package com.algotrader.starkex.types;

import java.math.BigInteger;
import java.math.RoundingMode;

public class OrderWithNonceAndQuoteAmount extends OrderWithNonce {
    private final String humanQuoteAmount;

    public OrderWithNonceAndQuoteAmount(Order order, BigInteger nonce, String humanQuoteAmount) {
        super(order, nonce);
        this.humanQuoteAmount = humanQuoteAmount;
    }

    public String getHumanQuoteAmount() {
        return humanQuoteAmount;
    }


    @Override
    protected boolean throwException() {
        return false;
    }

    @Override
    protected RoundingMode getRoundingMode() {
        return getOrder().getSide() == StarkwareOrderSide.BUY ? RoundingMode.UP : RoundingMode.DOWN;
    }

    @Override
    protected String getHumanAmount() {
        return humanQuoteAmount;
    }
}
