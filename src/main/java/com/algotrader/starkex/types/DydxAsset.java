package com.algotrader.starkex.types;

import java.math.BigInteger;

import static com.google.common.base.Strings.padEnd;
import static com.google.common.io.BaseEncoding.base16;
import static java.nio.charset.StandardCharsets.UTF_8;

public class DydxAsset {

    public static final DydxAsset USDC = new DydxAsset("USDC", new BigInteger("0", 16), 6);
    public static final DydxAsset ATOM = new DydxAsset("ATOM", new BigInteger("41544f4d2d37000000000000000000", 16), 7);

    private final String asset;
    private final BigInteger assetId;
    private final int resolution;

    public DydxAsset(
            final String asset,
            final int resolution) {
        this(asset,
                new BigInteger(padEnd(base16().encode((asset + "-" + resolution).getBytes(UTF_8)).toLowerCase(), 30, '0'), 16),
                resolution);
    }

    public DydxAsset(
            final String asset,
            final BigInteger assetId,
            final int resolution) {
        this.asset = asset;
        this.assetId = assetId;
        this.resolution = resolution;
    }

    public String getAsset() {
        return asset;
    }

    public String getMarket() {
        return asset + "-USD";
    }

    public BigInteger getAssetId() {
        return assetId;
    }

    public int getResolution() {
        return resolution;
    }

    @Override
    public String toString() {
        return "DydxAsset{" +
                "asset='" + asset + '\'' +
                ", assetId=" + assetId +
                ", resolution=" + resolution +
                '}';
    }

}
