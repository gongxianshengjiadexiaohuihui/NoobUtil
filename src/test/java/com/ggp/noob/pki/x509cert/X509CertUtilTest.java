package com.ggp.noob.pki.x509cert;

import com.ggp.noob.pki.key.KeyUtil;
import com.ggp.noob.pki.signature.SignatureUtil;
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
                "MIIBozCCAUqgAwIBAgIEEAAAYjAKBggqhkjOPQQDAjA3MQswCQYDVQQGEwJDTjEN\n" +
                "MAsGA1UECgwETklTVDEZMBcGA1UEAwwQTklTVOaguUNB5pu05pawMjAgFw0yMDAz\n" +
                "MTQwNjQyMTVaGA8yMDUwMDMwNzA2NDIxNVowNzELMAkGA1UEBhMCQ04xDTALBgNV\n" +
                "BAoMBE5JU1QxGTAXBgNVBAMMEE5JU1TmoLlDQeabtOaWsDIwWTATBgcqhkjOPQIB\n" +
                "BggqhkjOPQMBBwNCAARyuSCaH3KoI1dvdUIjA22y5XzMPvNkI/GrTEoUzUqJZx4s\n" +
                "WxvicpSD6V505PPbjSEiE0dLyO207TX52R1fmUW6o0IwQDAPBgNVHRMBAf8EBTAD\n" +
                "AQH/MA4GA1UdDwEB/wQEAwIBBjAdBgNVHQ4EFgQUDcD2z7zY90ZNEjZGddFX20E9\n" +
                "rcEwCgYIKoZIzj0EAwIDRwAwRAIgEKpwfrD+AOmziEfxhB+cdqNKdD33wKtkmSZH\n" +
                "InhC49wCIGIjZ7XuQj010KEq6hBpGxZcymNbNM1Qy3bG2++emMQd\n" +
                "-----END CERTIFICATE-----";
        X509Certificate certFromBase64Str = X509CertUtil.getCertFromBase64Str(str);
        System.out.println(certFromBase64Str.getSigAlgName());
        PublicKey publicKey = certFromBase64Str.getPublicKey();
        boolean flag = SignatureUtil.verify(certFromBase64Str.getSigAlgName(), publicKey, certFromBase64Str.getTBSCertificate(), certFromBase64Str.getSignature());
        System.out.println(flag);
    }

    @Test
    public void should_key_match_cert() throws Exception {
        X509Certificate certFromBase64Str = X509CertUtil.getCertFromFile("C:\\Users\\admin\\Desktop\\CA第五迭代\\test\\root.cer");
        System.out.println(certFromBase64Str.getPublicKey());
        PrivateKey key = KeyUtil.readPrivateKeyFromFile("C:\\Users\\admin\\Desktop\\CA第五迭代\\test\\private.key");
        System.out.println(key);
    }

}