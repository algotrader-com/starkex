package com.algotrader.starkex.signature;

import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;
import java.math.BigInteger;

/**
 * The Stark-friendly elliptic curve
 * <p>
 * y^2 = x^3 + a*x + b (mod P)
 *
 * @author ferat capar
 * @link https://docs.starkware.co/starkex-v3/crypto/stark-curve
 */

public class StarkCurve {
    /* Curve parameters*/
    private static final BigInteger P = new BigInteger("3618502788666131213697322783095070105623107215331596699973092056135872020481");
    private static final BigInteger A = BigInteger.ONE;
    private static final BigInteger B = new BigInteger("3141592653589793238462643383279502884197169399375105820974944592307816406665");
    private static final BigInteger N = new BigInteger("3618502788666131213697322783095070105526743751716087489154079457884512865583");
    private static final BigInteger H = BigInteger.ONE;
    private static final BigInteger Gx = new BigInteger("874739451078007766457464989774322083649278607533249481151382481072868806602");
    private static final BigInteger Gy = new BigInteger("152666792071518830868575557812948353041420400780739481342941381225525861407");
    /*******************/

    private static final StarkCurve instance = new StarkCurve();
    private final ECCurve curve;
    private final ECPoint pointG;
    
    private StarkCurve() {
        ECCurve dummy = new ECCurve.Fp(
                P,
                A,
                B,
                N,
                H);
        ECCurve.Config configure = dummy.configure();
        configure.setCoordinateSystem(ECCurve.COORD_AFFINE);
        curve = configure.create();
        pointG = curve.createPoint(Gx, Gy);
    }

    public static StarkCurve getInstance() {
        return instance;
    }

    public BigInteger getP() {
        return P;
    }

    public BigInteger getA() {
        return curve.getA().toBigInteger();
    }

    public BigInteger getB() {
        return curve.getB().toBigInteger();
    }

    public BigInteger getN() {
        return new BigInteger(curve.getOrder().toString());
    }

    public BigInteger getH() {
        return new BigInteger(curve.getCofactor().toString());
    }

    public BigInteger getGx() {
        return pointG.getXCoord().toBigInteger();
    }

    public BigInteger getGy() {
        return pointG.getYCoord().toBigInteger();
    }

    /**
     * @param privateKey private key
     * @param x          x coordinate of selected point on the curve
     * @param y          y coordinate of selected point on the curve
     * @return private key with respect to selected point
     */
    public ECPrivateKeyParameters createPrivateKeyParams(BigInteger privateKey, BigInteger x, BigInteger y) {
        return new ECPrivateKeyParameters(
                privateKey,
                new ECDomainParameters(curve, curve.createPoint(x, y), N));
    }

    public ECPrivateKeyParameters createPrivateKeyParams(BigInteger privateKey) {
        return createPrivateKeyParams(privateKey, getGx(), getGy());
    }

    public ECPoint createPoint(BigInteger x, BigInteger y) {
        return curve.createPoint(x, y);
    }


}
