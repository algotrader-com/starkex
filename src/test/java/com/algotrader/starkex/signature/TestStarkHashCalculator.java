package com.algotrader.starkex.signature;

import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import com.algotrader.starkex.converters.StarkwareOrderConverter;
import com.algotrader.starkex.exception.FieldExceedMaxException;
import com.algotrader.starkex.exception.HashingException;
import com.algotrader.starkex.exception.QuantumSizeException;
import com.algotrader.starkex.hashing.ConstantPoints;
import com.algotrader.starkex.hashing.PedersonHash;
import com.algotrader.starkex.hashing.StarkHashCalculator;
import com.algotrader.starkex.types.DydxMarket;
import com.algotrader.starkex.types.NetworkId;
import com.algotrader.starkex.types.Order;
import com.algotrader.starkex.types.OrderWithClientIdAndQuoteAmount;
import com.algotrader.starkex.types.OrderWithClientIdWithPrice;
import com.algotrader.starkex.types.StarkwareOrder;
import com.algotrader.starkex.types.StarkwareOrderSide;

class TestStarkHashCalculator {
    private final static String HASH_VALUE = "1690222932b6b9f7ec1a92f3950e5332892789e7531336114f588ed08de3a42";
    private final static StarkwareOrderConverter STARKWARE_ORDER_CONVERTER = new StarkwareOrderConverter();
    private final static StarkHashCalculator STARK_HASH_CALCULATOR=new StarkHashCalculator(new PedersonHash(ConstantPoints.POINTS.get(0)));
    private final static OrderWithClientIdWithPrice order = new OrderWithClientIdWithPrice(
            new Order("56277",
                    "1",
                    "0.001",
                    DydxMarket.ATOM_USD,
                    StarkwareOrderSide.BUY,
                    "2021-09-20T00:00:00.000Z"),
            "123456",
            "34.00"
    );

    @Test
    void testHashWithPrice() throws Exception {
        StarkwareOrder starkwareOrder = STARKWARE_ORDER_CONVERTER.fromOrderWithClientId(order, NetworkId.ROPSTEN);
        assertThat(STARK_HASH_CALCULATOR.calculateHash(starkwareOrder).toString(16), is(equalTo(HASH_VALUE)));
    }

    @Test
    void testHashWithQuateAmount() throws QuantumSizeException, NoSuchAlgorithmException, HashingException, FieldExceedMaxException {
        OrderWithClientIdAndQuoteAmount order = new OrderWithClientIdAndQuoteAmount(
                new Order("56277",
                        "1",
                        "0.001",
                        DydxMarket.ATOM_USD,
                        StarkwareOrderSide.BUY,
                        "2021-09-20T00:00:00.000Z"),
                "123456",
                "34.00"
        );

        StarkwareOrder starkwareOrder = STARKWARE_ORDER_CONVERTER.fromOrderWithClientId(order, NetworkId.ROPSTEN);
        assertThat(STARK_HASH_CALCULATOR.calculateHash(starkwareOrder).toString(16), is(equalTo(HASH_VALUE)));
    }

    @Test
    void testNonce() throws NoSuchAlgorithmException {
        assertThat(STARKWARE_ORDER_CONVERTER.nonceFromClientId("123456"),is(equalTo(new BigInteger("987524242"))));
    }


}
