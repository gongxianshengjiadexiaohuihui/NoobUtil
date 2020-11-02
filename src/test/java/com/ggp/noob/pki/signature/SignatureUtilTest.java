package com.ggp.noob.pki.signature;

import com.ggp.noob.common.Exception.SignatureException;
import com.ggp.noob.pki.key.KeyUtil;
import com.ggp.noob.pki.x509cert.X509CertUtil;
import org.junit.Assert;
import org.junit.Test;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;

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
    @Test
    public void test_verify() throws Exception{
        String str="-----BEGIN CERTIFICATE-----\n" +
                "MIIBxTCCAWygAwIBAgIEEAAAnjAKBggqgRzPVQGDdTArMQswCQYDVQQGEwJDTjEN\n" +
                "MAsGA1UECgwEWERKQTENMAsGA1UEAwwEUk9PVDAeFw0yMDA5MjgwMzU5NThaFw0y\n" +
                "MTA5MjgwMzU5NThaMB4xCzAJBgNVBAYTAkNOMQ8wDQYDVQQDDAZhZG1pbk8wWTAT\n" +
                "BgcqhkjOPQIBBggqgRzPVQGCLQNCAAT021GFclEOt8yWkAwbxEaZUhcxCcyemUfu\n" +
                "wLeTV6MTkfbu1mNu4DZVOktpchKOL4Sj5yQfaps5ziPUOmH+MIGUo4GKMIGHMFYG\n" +
                "A1UdIwRPME2AFBf0KN1Fy05dpA7KNz+28TovQ85ooS+kLTArMQswCQYDVQQGEwJD\n" +
                "TjENMAsGA1UECgwEWERKQTENMAsGA1UEAwwEUk9PVIIEEAAAAzAdBgNVHQ4EFgQU\n" +
                "fWVerVDVOv9lOQW6nB44z1c4rKMwDgYDVR0PAQH/BAQDAgP4MAoGCCqBHM9VAYN1\n" +
                "A0cAMEQCICcTLxEfsgbnsX6DM+rBdAZCwCGcYPhVx+9rmYAnnWqCAiBC6zloog+b\n" +
                "Ban0idGFS4XkQd1YGN9Qx6U8yBdhDY65+Q==\n" +
                "-----END CERTIFICATE-----\n";
        X509Certificate cert= X509CertUtil.getCertFromBase64Str(str);
        String plainText = "fd8846714a899a52d84c4da539b5bf0b955ef861da003eca3f1103879bfae0d24687831844308724";
        String sign="MIIC8QYKKoEcz1UGAQQCAqCCAuEwggLdAgEBMQYwBAYABQAwXwYJKoZIhvcNAQcBoFIEUGZkODg0NjcxNGE4OTlhNTJkODRjNGRhNTM5YjViZjBiOTU1ZWY4NjFkYTAwM2VjYTNmMTEwMzg3OWJmYWUwZDI0Njg3ODMxODQ0MzA4NzI0oIIByTCCAcUwggFsoAMCAQICBBAAAJ4wCgYIKoEcz1UBg3UwKzELMAkGA1UEBhMCQ04xDTALBgNVBAoMBFhESkExDTALBgNVBAMMBFJPT1QwHhcNMjAwOTI4MDM1OTU4WhcNMjEwOTI4MDM1OTU4WjAeMQswCQYDVQQGEwJDTjEPMA0GA1UEAwwGYWRtaW5PMFkwEwYHKoZIzj0CAQYIKoEcz1UBgi0DQgAE9NtRhXJRDrfMlpAMG8RGmVIXMQnMnplH7sC3k1ejE5H27tZjbuA2VTpLaXISji+Eo+ckH2qbOc4j1Dph/jCBlKOBijCBhzBWBgNVHSMETzBNgBQX9CjdRctOXaQOyjc/tvE6L0POaKEvpC0wKzELMAkGA1UEBhMCQ04xDTALBgNVBAoMBFhESkExDTALBgNVBAMMBFJPT1SCBBAAAAMwHQYDVR0OBBYEFH1lXq1Q1Tr/ZTkFupweOM9XOKyjMA4GA1UdDwEB/wQEAwID+DAKBggqgRzPVQGDdQNHADBEAiAnEy8RH7IG57F+gzPqwXQGQsAhnGD4Vcfva5mAJ51qggIgQus5aKIPmwWp9InRhUuF5EHdWBjfUMelPMgXYQ2OufkxgaEwgZ4CAQEwMzArMQswCQYDVQQGEwJDTjENMAsGA1UECgwEWERKQTENMAsGA1UEAwwEUk9PVAIEEAAAnjAMBggqgRzPVQGDEQUAMA0GCSqBHM9VAYItAQUABEcwRQIhAJgSWwIztQuNTZLr4iFqW2GftVztbS+uFDYne0SZm6bBAiBsJWeVW3F5Hu6Tiatk2rx+TlsFcXXMgmQk8smmy84IsA==";
        System.out.println(SignatureUtil.verifyBySm3WithSm2(cert.getPublicKey(),plainText,sign));
    }
}
