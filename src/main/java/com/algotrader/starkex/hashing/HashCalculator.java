package com.algotrader.starkex.hashing;

import com.algotrader.starkex.exception.FieldExceedMaxException;
import com.algotrader.starkex.exception.HashingException;

import java.math.BigInteger;

/**

 * Base class for hash calculators
 *
 * @param <T>
 * @author ferat capar
 */
public interface HashCalculator<T> {
    BigInteger calculateHash(T message) throws FieldExceedMaxException, HashingException;
}