package com.ggp.noob.util.pki.key;


import com.ggp.noob.util.pki.common.base.Constants;
import com.ggp.noob.util.pki.pem.PemUtil;
import org.junit.Test;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;

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