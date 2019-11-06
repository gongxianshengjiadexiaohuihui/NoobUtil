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
    public void should_be_success_when_signature_by_sm3WithSm2() {
        boolean flag = true;
        try {
            String source = "test";
            String result1 = SignatureUtil.signature(SignatureAlgorithmEnum.SM3_WITH_SM2.name, sm2PrivateKey, source);
            String result2 = SignatureUtil.signatureBySm3WithSm2(sm2PrivateKey, source);
            System.out.println("私钥：" + sm2PrivateKey);
            System.out.println("签名结果1：" + result1);
            System.out.println("签名结果2：" + result2);
            //TODO 同样数据两次签名结果不一致 猜测签名前随机填充了字节
        } catch (SignatureException e) {
            flag = false;
        }
        Assert.assertTrue(flag);
    }

    /**
     * 验签 sm2WithSm3
     */
    @Test
    public void should_be_success_when_verify_signature_with_sm3WithSm2() {
        boolean flag1;
        boolean flag2;
        try {
            String source = "test";
            String sign1 = SignatureUtil.signature(SignatureAlgorithmEnum.SM3_WITH_SM2.name, sm2PrivateKey, source);
            String sign2 = SignatureUtil.signatureBySm3WithSm2(sm2PrivateKey, source);
            flag1 = SignatureUtil.verify(SignatureAlgorithmEnum.SM3_WITH_SM2.name, sm2PublicKey, source, sign1);
            flag2 = SignatureUtil.verifyBySm3WithSm2(sm2PublicKey, source, sign2);
        } catch (SignatureException e) {
            flag1 = flag2 = false;
        }
        Assert.assertTrue(flag1 && flag2);
    }

}
