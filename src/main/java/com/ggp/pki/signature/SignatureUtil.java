package com.ggp.pki.signature;

import com.ggp.common.base.BCProvider;
import com.ggp.common.enums.pki.SignatureAlgorithmEnum;
import org.bouncycastle.util.encoders.Base64;

import java.security.*;

/**
 * @author: ggp
 * @Date: 2019/11/6 15:27
 * @Description: 签名验签工具类  provider是BC
 */
public class SignatureUtil extends BCProvider {
    /**
     * 签名
     *
     * @param signatureAlgorithmName 签名算法名字
     * @param key                    私钥
     * @param source                 签名原文
     * @return Base64字符串
     */
    public static String signature(String signatureAlgorithmName, PrivateKey key, String source) throws SignatureException {
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
        try {
            Signature signature = Signature.getInstance(signatureAlgorithmName);
            signature.initVerify(key);
            signature.update(source.getBytes());
            return signature.verify(Base64.decode(sign.getBytes("UTF-8")));
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
}
