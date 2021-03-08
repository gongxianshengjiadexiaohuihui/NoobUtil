package com.ggp.noob.util.pki.key;


import com.ggp.noob.util.pki.common.base.Constants;
import com.ggp.noob.util.pki.pem.PemUtil;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;
import org.junit.Test;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;

/**
 * @Author:ggp
 * @Date:2020-06-29 11:58
 * @Description:
 */
public class KeyUtilTest {

    @Test
    public void createSm2KeyPair() {
        KeyPair keyPair = KeyUtil.createSm2KeyPair();
        PemUtil.writeObjectToFile(keyPair, Constants.ROOT_PATH+"key.dat");
    }
    @Test
    public void test_parse_public() throws Exception{
        KeyPair keyPair = KeyUtil.createSm2KeyPair();
        String str = Base64.toBase64String(keyPair.getPublic().getEncoded());
        SubjectPublicKeyInfo subjectPublicKeyInfo = SubjectPublicKeyInfo.getInstance(Base64.decode(str));
        KeyFactory keyFactory = KeyFactory.getInstance(subjectPublicKeyInfo.getAlgorithm().getAlgorithm().getId(), BouncyCastleProvider.PROVIDER_NAME);
        PublicKey publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(subjectPublicKeyInfo.getEncoded()));
        System.out.println(publicKey);
    }
    @Test
    public void createRSAKeyPair() {
        KeyPair keyPair = KeyUtil.createRSAKeyPair(1024);
        RSAPublicKey publicKey = (RSAPublicKey)keyPair.getPublic();
        System.out.println(publicKey.getModulus().bitLength());
    }
    @Test
    public void test(){
        String temp = "";
        System.out.println(new String(temp.getBytes()));
    }
}