package com.ggp.pki.key;


import com.ggp.common.base.BCProvider;

import java.security.*;

/**
 * @author: ggp
 * @Date: 2019/11/6 16:06
 * @Description:
 */
public class KeyUtil extends BCProvider {


    /**
     * 生成RSA公私钥
     *
     * @return
     */
    public static KeyPair createSm2KeyPair() {
        SecureRandom random = new SecureRandom((String.valueOf(System.nanoTime())).getBytes());
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", "BC");
            keyPairGenerator.initialize(256, random);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            return keyPair;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 生成RSA公私钥
     *
     * @return
     */
    public static KeyPair createRSAKeyPair() {
        SecureRandom random = new SecureRandom((String.valueOf(System.nanoTime())).getBytes());
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", "BC");
            keyPairGenerator.initialize(2048, random);
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        return null;
    }
}
