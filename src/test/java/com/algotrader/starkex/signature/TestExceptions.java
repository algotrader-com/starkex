package com.algotrader.starkex.signature;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import com.algotrader.starkex.exception.FieldExceedMaxException;
import com.algotrader.starkex.exception.PedersonHashException;
import com.algotrader.starkex.exception.PedersonHashInputException;
import com.algotrader.starkex.exception.QuantumSizeException;
import com.algotrader.starkex.exception.SignException;
import com.algotrader.starkex.hashing.PedersonHash;

public class TestExceptions {
    @Test
    void testFieldExceedMaxException(){
        FieldExceedMaxException fieldExceedMaxException=new FieldExceedMaxException("testField");
        assertThat(fieldExceedMaxException.getLocalizedMessage(),is(equalTo("testField exceeds max value")));
        Locale.setDefault(new Locale("tr"));
        assertThat(fieldExceedMaxException.getLocalizedMessage(),is(equalTo("testField max degeri asti")));
    }

    @Test
    void testPedersonHashException(){
        PedersonHashException pedersonHashException=new PedersonHashException();
        assertThat(pedersonHashException.getLocalizedMessage(),is(equalTo("Error computing pedersen hash")));
    }

    @Test
    void testPedersonHashInputException(){
        PedersonHashInputException pedersonHashInputException=new PedersonHashInputException("testValue");
        assertThat(pedersonHashInputException.getLocalizedMessage(),is(equalTo("Input to pedersen hash out of range: testValue")));
    }

    @Test
    void testQuantumSizeException(){
        QuantumSizeException quantumSizeException=new QuantumSizeException(BigInteger.ONE,BigInteger.TEN);
        assertThat(quantumSizeException.getLocalizedMessage(),is(equalTo("Amount 1 is not a multiple of the quantum size 10")));
    }

    @Test
    void tesSignException(){
        SignException signException=new SignException("invalidHashLength",64);
        assertThat(signException.getLocalizedMessage(),is(equalTo("Invalid hash length: 64 !== 63")));

    }
}
