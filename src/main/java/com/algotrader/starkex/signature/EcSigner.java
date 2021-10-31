package com.algotrader.starkex.signature;

import com.algotrader.starkex.exception.SignException;

import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.crypto.signers.HMacDSAKCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigInteger;

/**
 * Stark curve signer class
 *
 * @author ferat capar
 */
public class EcSigner {
    private static final Logger logger = LoggerFactory.getLogger(EcSigner.class);
    private final StarkCurve curve;

    public EcSigner(StarkCurve curve) {
        this.curve = curve;
    }

    /**
     * @param privateKey private key
     * @param message    message to be signed
     * @return signed message (ecdsa signature)
     */
    public Signature sign(BigInteger privateKey, BigInteger message) throws SignException {
        HMacDSAKCalculator hMacDSAKCalculator = new HMacDSAKCalculator(new SHA256Digest());
        ECDSASigner signer = new ECDSASigner(hMacDSAKCalculator);
        signer.init(true, curve.createPrivateKeyParams(privateKey));
        BigInteger[] signature = signer.generateSignature(fixMessageLength(message).toByteArray());
        logger.trace("signature created for message {} r :{} s:{}", message, signature[0], signature[1]);
        return new Signature(signature[0], signature[1]);
    }

    private BigInteger fixMessageLength(BigInteger message) throws SignException {
        String hashHex = message.toString(16);
        if (hashHex.length() <= 62) {
            // In this case, messageHash should not be transformed, as the byteLength() is at most 31,
            // so delta < 0 (see _truncateToN).
            return message;
        }
        if (hashHex.length() != 63) {
            logger.error("Invalid hash length: {} !== 63", hashHex.length());
            throw new SignException("invalidHashLength", hashHex.length());
        }
        // In this case delta will be 4 so we perform a shift-left of 4 bits.
        return message.shiftLeft(4);
    }
}
