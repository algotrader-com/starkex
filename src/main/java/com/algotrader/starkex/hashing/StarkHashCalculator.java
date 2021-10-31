package com.algotrader.starkex.hashing;

import com.algotrader.starkex.exception.FieldExceedMaxException;
import com.algotrader.starkex.exception.HashingException;
import com.algotrader.starkex.types.StarkwareOrder;

import java.math.BigInteger;

/**
 * calculates hash using given hash function
 * input message is StarkwareOrder
 *
 * @author ferat capar
 * @see StarkwareOrder,HashFunction
 */
public class StarkHashCalculator extends HashCalculator<StarkwareOrder> {
    private static final String LIMIT_ORDER_WITH_FEES = "3";
    private static final int ORDER_PADDING_BITS = 17;
    private final HashFunction hashFunction;


    public StarkHashCalculator(HashFunction hashFunction) {
        this.hashFunction = hashFunction;
    }

    @Override
    public BigInteger calculateHash(StarkwareOrder message) throws FieldExceedMaxException, HashingException {
        BigInteger assetIdFeeBn = message.getAssetIdFee();
        BigInteger quantumsAmountFeeBn = message.getQuantumsAmountFee();
        BigInteger nonceBn = message.getNonce();
        BigInteger positionIdBn = new BigInteger(message.getPositionId());
        BigInteger expirationEpochHours = new BigInteger(message.getExpirationEpochHours().toString());

        BigInteger assetIdSellBn;
        BigInteger assetIdBuyBn;
        BigInteger quantumsAmountSellBn;
        BigInteger quantumsAmountBuyBn;

        if (message.getStarkwareAmounts().isBuyingSynthetic()) {
            assetIdSellBn = message.getStarkwareAmounts().getAssetIdCollateral();
            assetIdBuyBn = message.getStarkwareAmounts().getAssetIdSynthetic();
            quantumsAmountSellBn = message.getStarkwareAmounts().getQuantumsAmountCollateral();
            quantumsAmountBuyBn = message.getStarkwareAmounts().getQuantumsAmountSynthetic();
        } else {
            assetIdSellBn = message.getStarkwareAmounts().getAssetIdSynthetic();
            assetIdBuyBn = message.getStarkwareAmounts().getAssetIdCollateral();
            quantumsAmountSellBn = message.getStarkwareAmounts().getQuantumsAmountSynthetic();
            quantumsAmountBuyBn = message.getStarkwareAmounts().getQuantumsAmountCollateral();
        }

        checkFieldSizes(message, assetIdFeeBn, quantumsAmountFeeBn, nonceBn, positionIdBn, expirationEpochHours);

        BigInteger orderPart1 = new BigInteger(quantumsAmountSellBn.toString())
                .shiftLeft(OrderFieldBitLengths.QUANTUMS_AMOUNT).add(quantumsAmountBuyBn)
                .shiftLeft(OrderFieldBitLengths.QUANTUMS_AMOUNT).add(quantumsAmountFeeBn)
                .shiftLeft(OrderFieldBitLengths.NONCE).add(nonceBn);

        BigInteger orderPart2 = new BigInteger(LIMIT_ORDER_WITH_FEES)
                .shiftLeft(OrderFieldBitLengths.POSITION_ID).add(positionIdBn) // Repeat (1/3).
                .shiftLeft(OrderFieldBitLengths.POSITION_ID).add(positionIdBn) // Repeat (2/3).
                .shiftLeft(OrderFieldBitLengths.POSITION_ID).add(positionIdBn) // Repeat (3/3).
                .shiftLeft(OrderFieldBitLengths.EXPIRATION_EPOCH_HOURS).add(expirationEpochHours)
                .shiftLeft(ORDER_PADDING_BITS);

        BigInteger cacheAsset = hashFunction.hashFromCache(assetIdSellBn, assetIdBuyBn);
        BigInteger assetsBn = hashFunction.hashFromCache(cacheAsset, assetIdFeeBn);

        return hashFunction.createHash(
                hashFunction.createHash(assetsBn, orderPart1),
                orderPart2
        );
    }

    private void checkFieldSizes(StarkwareOrder message, BigInteger assetIdFeeBn, BigInteger quantumsAmountFeeBn, BigInteger nonceBn, BigInteger positionIdBn, BigInteger expirationEpochHours) throws FieldExceedMaxException {
        if (message.getStarkwareAmounts().getAssetIdSynthetic().bitLength() > OrderFieldBitLengths.ASSET_ID_SYNTHETIC) {
            throw new FieldExceedMaxException("assetIdSynthetic");
        }
        if (message.getStarkwareAmounts().getAssetIdCollateral().bitLength() > OrderFieldBitLengths.ASSET_ID_COLLATERAL) {
            throw new FieldExceedMaxException("assetIdCollateral");
        }
        if (assetIdFeeBn.bitLength() > OrderFieldBitLengths.ASSET_ID_FEE) {
            throw new FieldExceedMaxException("assetIdFee");
        }
        if (message.getStarkwareAmounts().getQuantumsAmountSynthetic().bitLength() > OrderFieldBitLengths.QUANTUMS_AMOUNT) {
            throw new FieldExceedMaxException("quantumsAmountSynthetic");
        }
        if (message.getStarkwareAmounts().getQuantumsAmountCollateral().bitLength() > OrderFieldBitLengths.QUANTUMS_AMOUNT) {
            throw new FieldExceedMaxException("quantumsAmountCollateral");
        }
        if (quantumsAmountFeeBn.bitLength() > OrderFieldBitLengths.QUANTUMS_AMOUNT) {
            throw new FieldExceedMaxException("quantumsAmountFee");
        }
        if (nonceBn.bitLength() > OrderFieldBitLengths.NONCE) {
            throw new FieldExceedMaxException("nonce");
        }
        if (positionIdBn.bitLength() > OrderFieldBitLengths.POSITION_ID) {
            throw new FieldExceedMaxException("positionId");
        }
        if (expirationEpochHours.bitLength() > OrderFieldBitLengths.EXPIRATION_EPOCH_HOURS) {
            throw new FieldExceedMaxException("expirationEpochHours");
        }
    }


    static class OrderFieldBitLengths {
        public static final int ASSET_ID_SYNTHETIC = 128;
        public static final int ASSET_ID_COLLATERAL = 250;
        public static final int ASSET_ID_FEE = 250;
        public static final int QUANTUMS_AMOUNT = 64;
        public static final int NONCE = 32;
        public static final int POSITION_ID = 64;
        public static final int EXPIRATION_EPOCH_HOURS = 32;

        private OrderFieldBitLengths() {
        }
    }
}
