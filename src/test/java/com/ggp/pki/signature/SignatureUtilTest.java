package com.ggp.pki.signature;

import com.ggp.common.Exception.SignatureException;
import com.ggp.pki.key.KeyUtil;
import org.junit.Assert;
import org.junit.Test;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author: ggp
 * @Date: 2019/11/6 15:21
 * @Description:
 */
public class SignatureUtilTest {
    public static KeyPair sm2KeyPair;
    public static PrivateKey sm2PrivateKey;
    public static PublicKey sm2PublicKey;

    public static KeyPair rsaKeyPair;
    public static PrivateKey rsaPrivateKey;
    public static PublicKey rsaPublicKey;

    public static KeyPair dsaKeyPair;
    public static PrivateKey dsaPrivateKey;
    public static PublicKey dsaPublicKey;

    static {
        sm2KeyPair = KeyUtil.createSm2KeyPair();
        sm2PublicKey = sm2KeyPair.getPublic();
        sm2PrivateKey = sm2KeyPair.getPrivate();

        rsaKeyPair = KeyUtil.createRSAKeyPair(1024);
        rsaPrivateKey = rsaKeyPair.getPrivate();
        rsaPublicKey = rsaKeyPair.getPublic();

        dsaKeyPair = KeyUtil.createECDSAKeyPair();
        dsaPrivateKey = dsaKeyPair.getPrivate();
        dsaPublicKey = dsaKeyPair.getPublic();

    }

    /**
     * 签名 sm2WithSm3
     */
    @Test
    public void should_be_success_when_signature_by_sm3WithSm2() {
        boolean flag = true;
        try {
            String source = "test";
            String result = SignatureUtil.signatureBySm3WithSm2(sm2PrivateKey, source);
            System.out.println("私钥：" + sm2PrivateKey);
            System.out.println("签名结果：" + result);
        } catch (SignatureException e) {
            flag = false;
            e.printStackTrace();
        }
        Assert.assertTrue(flag);
    }

    /**
     * 验签 sm2WithSm3
     */
    @Test
    public void should_be_success_when_verify_signature_with_sm3WithSm2() {
        boolean flag;
        try {
            String source = "test";
            String sign2 = SignatureUtil.signatureBySm3WithSm2(sm2PrivateKey, source);
            flag = SignatureUtil.verifyBySm3WithSm2(sm2PublicKey, source, sign2);
        } catch (SignatureException e) {
            flag = false;
            e.printStackTrace();
        }
        Assert.assertTrue(flag);
    }

    /**
     * 签名 SHA1WithRSA
     */
    @Test
    public void should_be_success_when_signature_by_SHA1WithRSA() {
        boolean flag = true;
        try {
            String source = "test";
            String result = SignatureUtil.signatureBySHA1WithRSA(rsaPrivateKey, source);
            System.out.println("私钥：" + rsaPrivateKey);
            System.out.println("签名结果：" + result);
        } catch (SignatureException e) {
            flag = false;
            e.printStackTrace();
        }
        Assert.assertTrue(flag);
    }

    /**
     * 验签 SHA1WithRSA
     */
    @Test
    public void should_be_success_when_verify_by_SHA1WithRSA() {
        boolean flag;
        try {
            String source = "test";
            String sign = SignatureUtil.signatureBySHA1WithRSA(rsaPrivateKey, source);
            flag = SignatureUtil.verifyBySHA1WithRSA(rsaPublicKey, source, sign);
        } catch (SignatureException e) {
            flag = false;
            e.printStackTrace();
        }
        Assert.assertTrue(flag);
    }

    /**
     * 签名 SHA256WithRSA
     */
    @Test
    public void should_be_success_when_signature_by_SHA256WithRSA() {
        boolean flag = true;
        try {
            String source = "test";
            String result = SignatureUtil.signatureBySHA256WithRSA(rsaPrivateKey, source);
            System.out.println("私钥：" + rsaPrivateKey);
            System.out.println("签名结果：" + result);
        } catch (SignatureException e) {
            flag = false;
            e.printStackTrace();
        }
        Assert.assertTrue(flag);
    }

    /**
     * 验签 SHA256WithRSA
     */
    @Test
    public void should_be_success_when_verify_by_SHA256WithRSA() {
        boolean flag;
        try {
            String source = "test";
            String sign = SignatureUtil.signatureBySHA256WithRSA(rsaPrivateKey, source);
            flag = SignatureUtil.verifyBySHA256WithRSA(rsaPublicKey, source, sign);
        } catch (SignatureException e) {
            flag = false;
            e.printStackTrace();
        }
        Assert.assertTrue(flag);
    }

    /**
     * 签名 SHA256WithECDSA
     */
    @Test
    public void should_be_success_when_signature_by_SHA256WithECDSA() {
      boolean flag = true;
        try {
            String source = "test";
            String result = SignatureUtil.sigatureBySHA256WithECDSA(dsaPrivateKey,source);
            System.out.println("私钥："+dsaPrivateKey);
            System.out.println("签名结果："+result);
        } catch (SignatureException e) {
            flag = false;
            e.printStackTrace();
        }
        Assert.assertTrue(flag);
    }

    /**
     * 验签 SHA256WithECDSA
     */
    @Test
    public void should_be_success_when_verify_by_SHA256WithECDSA() {
        boolean flag;
        try {
            String source="test";
            String sign = SignatureUtil.sigatureBySHA256WithECDSA(dsaPrivateKey,source);
            flag = SignatureUtil.verifyBySHA256WithECDSA(dsaPublicKey,source,sign);
        } catch (SignatureException e) {
            flag = false;
            e.printStackTrace();
        }
        Assert.assertTrue(flag);
    }
}
