package com.ggp.pki.signature;

import com.ggp.common.enums.pki.SignatureAlgorithmEnum;
import com.ggp.pki.key.KeyUtil;
import org.junit.Assert;
import org.junit.Test;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;

/**
 * @author: ggp
 * @Date: 2019/11/6 15:21
 * @Description:
 */
public class SignatureUtilTest {
    public static KeyPair sm2KeyPair;
    public static PrivateKey sm2PrivateKey;
    public static PublicKey sm2PublicKey;

    static {
        sm2KeyPair = KeyUtil.createSm2KeyPair();
        sm2PublicKey = sm2KeyPair.getPublic();
        sm2PrivateKey = sm2KeyPair.getPrivate();
    }

    /**
     * 签名 sm2WithSm3
     */
    @Test
    public void should_be_no_exception_when_signature_by_sm3WithSm2(){
        boolean flag = true;
        try {
            String source = "test";
            String result = SignatureUtil.signature(SignatureAlgorithmEnum.SM3_WITH_SM2.name, sm2PrivateKey, source);
            System.out.println("私钥："+sm2PrivateKey);
            System.out.println("签名结果：" + result);
        } catch (SignatureException e) {
            flag = false;
        }
        Assert.assertTrue(flag);
    }

    /**
     * 验签 sm2WithSm3
     */
    @Test
    public void should_be_success_when_verify_signature_with_sm3WithSm2(){
        boolean flag = true;
        try {
            String source = "test";
            String sign = SignatureUtil.signature(SignatureAlgorithmEnum.SM3_WITH_SM2.name, sm2PrivateKey, source);
            flag = SignatureUtil.verify(SignatureAlgorithmEnum.SM3_WITH_SM2.name,sm2PublicKey,source,sign);
        } catch (SignatureException e) {
            flag = false;
        }
        Assert.assertTrue(flag);
    }
}
