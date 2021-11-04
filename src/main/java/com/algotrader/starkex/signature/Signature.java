package com.algotrader.starkex.signature;

import java.math.BigInteger;

/**
 * ECDSA signature representation
 *
 * @author ferat capar
 */
public class Signature {
    private final BigInteger r;
    private final BigInteger s;

    public Signature(BigInteger r, BigInteger s) {
        this.r = r;
        this.s = s;
    }

    public BigInteger r() {
        return r;
    }

    public BigInteger s() {
        return s;
    }

    @Override
    public String toString() {
        return String.format("%1$64s%2$64s", this.r.toString(16), this.s.toString(16)).replace(" ", "0");
    }

}
