package com.ggp.noob.pki.key;


import com.ggp.noob.common.base.AbstractProvider;
import org.bouncycastle.asn1.nist.NISTNamedCurves;
import org.bouncycastle.asn1.sec.SECObjectIdentifiers;

import java.security.*;
import java.security.spec.ECGenParameterSpec;

/**
 * @author: ggp
 * @Date: 2019/11/6 16:06
 * @Description:
 */
public class KeyUtil extends AbstractProvider {


    /**
     * 生成sm2公私钥
     *
     * @return
     */
    public static KeyPair createSm2KeyPair() {
        SecureRandom random = new SecureRandom((String.valueOf(System.nanoTime())).getBytes());
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", "BC");
            keyPairGenerator.initialize(new ECGenParameterSpec("sm2p256v1"), random);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            return keyPair;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 生成EC-DSA公私钥
     * @return
     */
    public static KeyPair createECDSAKeyPair(){
        SecureRandom random = new SecureRandom((String.valueOf(System.nanoTime())).getBytes());
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", "BC");
            keyPairGenerator.initialize(new ECGenParameterSpec(NISTNamedCurves.getName(SECObjectIdentifiers.secp256r1)), random);
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
    public static KeyPair createRSAKeyPair(int size) {
        SecureRandom random = new SecureRandom((String.valueOf(System.nanoTime())).getBytes());
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", "BC");
            keyPairGenerator.initialize(size, random);
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        return null;
    }
}
