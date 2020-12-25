package com.ggp.noob.util.pki.signature;


import com.ggp.noob.util.pki.common.base.AbstractProvider;
import com.ggp.noob.util.pki.common.enums.pki.SignatureAlgorithmEnum;
import org.bouncycastle.util.encoders.Base64;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;


/**
 * @author: ggp
 * @Date: 2019/11/6 15:27
 * @Description: 签名验签工具类  provider是BC
 */
public class SignatureUtil extends AbstractProvider {
    /**
     * 签名
     *
     * @param signatureAlgorithmName 签名算法名字
     * @param key                    私钥
     * @param source                 签名原文
     * @return Base64字符串
     */
    public static String signature(String signatureAlgorithmName, PrivateKey key, String source) throws SignatureException {
        if (!SignatureAlgorithmEnum.contain(signatureAlgorithmName)) {
            throw new SignatureException("不支持的签名算法");
        }
        try {
            Signature signature = Signature.getInstance(signatureAlgorithmName);
            signature.initSign(key);
            signature.update(source.getBytes());
            byte[] out = signature.sign();
            return new String(Base64.encode(out));
        } catch (Exception e) {
            throw new SignatureException("签名失败", e);
        }
    }

    /**
     * 验签
     *
     * @param signatureAlgorithmName 签名算法名字
     * @param key                    公钥
     * @param source                 签名原文
     * @param sign                   签名值
     * @return
     */
    public static boolean verify(String signatureAlgorithmName, PublicKey key, String source, String sign) throws SignatureException {
           return verify(signatureAlgorithmName,key,source.getBytes(),sign.getBytes());
    }
    /**
     * 验签
     *
     * @param signatureAlgorithmName 签名算法名字
     * @param key                    公钥
     * @param source                 签名原文
     * @param sign                   签名值
     * @return
     */
    public static boolean verify(String signatureAlgorithmName, PublicKey key, byte[] source, byte[] sign) throws SignatureException {
        if (!SignatureAlgorithmEnum.contain(signatureAlgorithmName)) {
            throw new SignatureException("不支持的签名算法");
        }
        try {
            Signature signature = Signature.getInstance(signatureAlgorithmName);
            signature.initVerify(key);
            signature.update(source);
            return signature.verify(Base64.decode(sign));
        } catch (Exception e) {
            throw new SignatureException("验签失败", e);
        }
    }

    /**
     * 签名 签名算法为SM3WithSM2
     *
     * @param key    私钥
     * @param source 签名原文
     * @return
     */
    public static String signatureBySm3WithSm2(PrivateKey key, String source) throws SignatureException {
        return signature(SignatureAlgorithmEnum.SM3_WITH_SM2.name, key, source);
    }

    /**
     * 验签 签名算法为SM3WithSM2
     *
     * @param key    公钥
     * @param source 签名原文
     * @param sign   签名值
     * @return
     */
    public static boolean verifyBySm3WithSm2(PublicKey key, String source, String sign) throws SignatureException {
        return verify(SignatureAlgorithmEnum.SM3_WITH_SM2.name, key, source, sign);
    }

    /**
     * 签名 签名算法为SH1WithRSA
     *
     * @param key    私钥
     * @param source 签名原文
     * @return
     * @throws SignatureException
     */
    public static String signatureBySHA1WithRSA(PrivateKey key, String source) throws SignatureException {
        return signature(SignatureAlgorithmEnum.SHA1_WITH_RSA.name, key, source);
    }

    /**
     * 验签 签名算法为SHA1WithRSA
     *
     * @param key    公钥
     * @param source 签名原文
     * @param sign   签名值
     * @return
     * @throws SignatureException
     */
    public static boolean verifyBySHA1WithRSA(PublicKey key, String source, String sign) throws SignatureException {
        return verify(SignatureAlgorithmEnum.SHA1_WITH_RSA.name, key, source, sign);
    }

    /**
     * 签名 签名算法为SHA256WithRSA
     *
     * @param key    私钥
     * @param source 签名原文
     * @return
     * @throws SignatureException
     */
    public static String signatureBySHA256WithRSA(PrivateKey key, String source) throws SignatureException {
        return signature(SignatureAlgorithmEnum.SHA256_WITH_RSA.name, key, source);
    }

    /**
     * 验签 签名算法为SHA256WithRSA
     *
     * @param key    公钥
     * @param source 签名原文
     * @param sign   签名值
     * @return
     * @throws SignatureException
     */
    public static boolean verifyBySHA256WithRSA(PublicKey key, String source, String sign) throws SignatureException {
        return verify(SignatureAlgorithmEnum.SHA256_WITH_RSA.name, key, source, sign);
    }

    /**
     * 签名 签名算法为SHA256WithECDSA
     *
     * @param key    私钥钥
     * @param source 签名原文
     * @return
     * @throws SignatureException
     */
    public static String sigatureBySHA256WithECDSA(PrivateKey key, String source) throws SignatureException {
        return signature(SignatureAlgorithmEnum.SHA256_WITH_ECDSA.name, key, source);
    }

    /**
     * 验签 签名算法为SHA256WithECDSA
     *
     * @param key    公钥
     * @param source 签名原文
     * @param sign   签名值
     * @return
     * @throws SignatureException
     */
    public static boolean verifyBySHA256WithECDSA(PublicKey key, String source, String sign) throws SignatureException {
        return verify(SignatureAlgorithmEnum.SHA256_WITH_ECDSA.name, key, source, sign);
    }
}
