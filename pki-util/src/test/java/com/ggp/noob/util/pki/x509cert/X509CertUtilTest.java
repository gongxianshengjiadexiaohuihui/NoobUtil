package com.ggp.noob.util.pki.x509cert;


import com.ggp.noob.util.pki.key.KeyUtil;
import com.ggp.noob.util.pki.signature.SignatureUtil;
import org.junit.Test;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;


/**
 * @author: ggp
 * @Date: 2020/3/12 08:58
 * @Description:
 */
public class X509CertUtilTest {
    @Test
    public void should_get_X509Cert_from_base64() throws Exception {
        String str = "-----BEGIN CERTIFICATE-----\n" +
                "MIIBmjCCAUCgAwIBAgIEEAAAADAKBggqhkjOPQQDAjAyMQswCQYDVQQGEwJDTjEN\n" +
                "MAsGA1UECgwEWERKQTEUMBIGA1UEAwwLQ0FfTklTVF8xMDIwIBcNMjAwOTEwMTIw\n" +
                "MzM5WhgPMjA1MDA5MDMxMjAzMzlaMDIxCzAJBgNVBAYTAkNOMQ0wCwYDVQQKDARY\n" +
                "REpBMRQwEgYDVQQDDAtDQV9OSVNUXzEwMjBZMBMGByqGSM49AgEGCCqGSM49AwEH\n" +
                "A0IABJF5XEfYXdscIlk8lhwNse8wAZ7QwjunKax3dQlGtdwkm+dx2/dyVkxne6l/\n" +
                "8ep9Rt5t5HMtXBjcSxuTv/4/21mjQjBAMB0GA1UdDgQWBBT0Ye/ZmIbcdPat1APC\n" +
                "gcZrENxzEjAOBgNVHQ8BAf8EBAMCAQYwDwYDVR0TAQH/BAUwAwEB/zAKBggqhkjO\n" +
                "PQQDAgNIADBFAiEAj5JFaOZRK+b4uUXEgVx0zAsa8/6HYpR7bq1AKz/Qe7QCIGIZ\n" +
                "3PHr4+CzX9gb54K6mPEKS9WyW1COQOyw4uLhV5bl\n" +
                "-----END CERTIFICATE-----\n";
        X509Certificate certFromBase64Str = X509CertUtil.getCertFromBase64Str(str);
        System.out.println(certFromBase64Str.getSigAlgName());
        PublicKey publicKey = certFromBase64Str.getPublicKey();
        boolean flag = SignatureUtil.verify(certFromBase64Str.getSigAlgName(), publicKey, certFromBase64Str.getTBSCertificate(), certFromBase64Str.getSignature());
        System.out.println(flag);
    }

    @Test
    public void should_key_match_cert() throws Exception {
        X509Certificate certFromBase64Str =  X509CertUtil.getCertFromFile("C:\\Users\\admin\\Desktop\\CA第五迭代\\test\\root.cer");
        System.out.println(certFromBase64Str.getPublicKey());
        PrivateKey key = KeyUtil.readPrivateKeyFromFile("C:\\Users\\admin\\Desktop\\CA第五迭代\\test\\private.key");
        System.out.println(key);
    }

}