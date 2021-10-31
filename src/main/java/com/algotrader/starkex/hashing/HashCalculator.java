package com.algotrader.starkex.hashing;

import com.algotrader.starkex.exception.FieldExceedMaxException;
import com.algotrader.starkex.exception.HashingException;

import java.math.BigInteger;

/**
 * Base class for hash calculators
 *
 *
 * @param <T>
 *
 * @author ferat capar
 */
public abstract class HashCalculator<T> {
    public String getHashString(T message) throws FieldExceedMaxException, HashingException {
        return String.format("%1$" + 63 + "s", calculateHash(message).toString(16)).replace(' ', '0');
    }

    public abstract BigInteger calculateHash(T message) throws FieldExceedMaxException, HashingException;
}
