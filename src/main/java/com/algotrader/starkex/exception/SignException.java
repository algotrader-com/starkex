package com.algotrader.starkex.exception;

public class SignException extends StarkException{
    public SignException(String messageKey, Object... params) {
        super(messageKey, params);
    }
}
