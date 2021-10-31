package com.algotrader.starkex.exception;

/**
 * Exception to pederson hash function
 *
 * @author ferat capar
 */
public class PedersonHashException extends HashingException {
    public PedersonHashException() {
        super("pedersonHash");
    }
}
