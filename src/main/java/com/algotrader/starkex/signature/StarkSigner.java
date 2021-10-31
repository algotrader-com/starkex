package com.algotrader.starkex.signature;

import com.algotrader.starkex.converters.StarkwareOrderConverter;
import com.algotrader.starkex.exception.FieldExceedMaxException;
import com.algotrader.starkex.exception.HashingException;
import com.algotrader.starkex.exception.QuantumSizeException;
import com.algotrader.starkex.exception.SignException;
import com.algotrader.starkex.hashing.ConstantPoints;
import com.algotrader.starkex.hashing.PedersonHash;
import com.algotrader.starkex.hashing.StarkHashCalculator;
import com.algotrader.starkex.types.NetworkId;
import com.algotrader.starkex.types.OrderWithClientId;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

/**
 * creates signture from OrderWithClientId privatekey and networkid
 *
 * @author ferat capar
 */
public class StarkSigner {
    private static final StarkHashCalculator STARK_HASH_CALCULATOR = new StarkHashCalculator(new PedersonHash(ConstantPoints.POINTS.get(0)));
    private static final EcSigner EC_SIGNER = new EcSigner(StarkCurve.getInstance());
    private static final StarkwareOrderConverter STARKWARE_ORDER_CONVERTER = new StarkwareOrderConverter();

    public Signature sign(OrderWithClientId order, NetworkId networkId, BigInteger privateKey) throws QuantumSizeException, NoSuchAlgorithmException, FieldExceedMaxException, HashingException, SignException {
        return EC_SIGNER.sign(privateKey, STARK_HASH_CALCULATOR.calculateHash(STARKWARE_ORDER_CONVERTER.fromOrderWithClientId(order, networkId)));
    }
}
