package com.algotrader.starkex.signature;

import com.algotrader.starkex.exception.FieldExceedMaxException;
import com.algotrader.starkex.exception.HashingException;
import com.algotrader.starkex.exception.QuantumSizeException;
import com.algotrader.starkex.exception.SignException;
import com.algotrader.starkex.types.DydxAsset;
import com.algotrader.starkex.types.NetworkId;
import com.algotrader.starkex.types.Order;
import com.algotrader.starkex.types.OrderWithClientIdAndQuoteAmount;
import com.algotrader.starkex.types.StarkwareOrderSide;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class TestStarkSigner {

    @Test
    void testSign() throws QuantumSizeException, NoSuchAlgorithmException, FieldExceedMaxException, HashingException, SignException {

        String EXPECTED_SIGNATURE = "06bca455438f4e337e11dff3897d85fdd425be2eb51bd0db92f34527965934e7003376e6726048f2bb23a6f89e7a7528c8cd832493431dd6c685148517ec752f";
        BigInteger PRIVATE_KEY = new BigInteger("07230d8f6fcba9afb8eea3aa67119b5a1bc117500186c384b5aaee85dafbb64c", 16);

        OrderWithClientIdAndQuoteAmount order = new OrderWithClientIdAndQuoteAmount(
                new Order("56277",
                        "1",
                        "0.001",
                        new DydxAsset("ATOM", 7),
                        StarkwareOrderSide.BUY,
                        "2021-09-20T00:00:00.000Z"),
                "123456",
                "34.00"
        );

        StarkSigner starkSigner = new StarkSigner();
        Signature signature = starkSigner.sign(order, NetworkId.ROPSTEN, PRIVATE_KEY);
        assertThat(signature.toString(), is(equalTo(EXPECTED_SIGNATURE)));
    }

    @Test
    void testSign2() throws QuantumSizeException, NoSuchAlgorithmException, FieldExceedMaxException, HashingException, SignException {

        String EXPECTED_SIGNATURE = "0121b9b648ee938ca403bb865ab26aa442edb9b7f2c40edf7f86aae0b9686429014cf84c77fe3701ea4516a9e77c31890a69ec72f1af0c64cada7cbf14313c58";
        BigInteger PRIVATE_KEY = new BigInteger("515282606d04ca75bfda0b2f3f92f9ad591f0153ff79ccac99f0a61016a4c5", 16);

        OrderWithClientIdAndQuoteAmount order = new OrderWithClientIdAndQuoteAmount(
                new Order("123456",
                        "1.00",
                        "0.0015",
                        new DydxAsset("ZED", 8),
                        StarkwareOrderSide.SELL,
                        "2021-11-03T16:22:23Z"),
                "WyHPW57ZKGwcie18UbEBGcry2QervYgYSG1Fm6YG",
                "200.00"
        );

        StarkSigner starkSigner = new StarkSigner();
        Signature signature = starkSigner.sign(order, NetworkId.MAINNET, PRIVATE_KEY);
        assertThat(signature.toString(), is(equalTo(EXPECTED_SIGNATURE)));
    }
}
