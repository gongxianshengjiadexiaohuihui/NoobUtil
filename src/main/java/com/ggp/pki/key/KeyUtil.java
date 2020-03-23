package com.ggp.pki.key;


import com.ggp.common.base.AbstractProvider;
import com.ggp.common.base.Constants;
import org.bouncycastle.asn1.nist.NISTNamedCurves;
import org.bouncycastle.asn1.sec.SECObjectIdentifiers;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;

import java.security.*;
import java.security.spec.ECGenParameterSpec;

import static com.ggp.pki.pem.PemUtil.readPEM;

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
     *
     * @return
     */
    public static KeyPair createECDSAKeyPair() {
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
     * @param size 密钥长度  一般为1024和2048
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


    /**
     * 从文件中读取公钥
     *
     * @param path 文件路径
     * @return
     */
    public static PublicKey readPublicKeyFromFile(String path) throws Exception {
        Object obj = readPEM(path);
        SubjectPublicKeyInfo info = SubjectPublicKeyInfo.getInstance(obj);
        JcaPEMKeyConverter converter = new JcaPEMKeyConverter().setProvider(Constants.provider);
        return converter.getPublicKey(info);

    }

    /**
     * 从文件中读取私钥
     *
     * @param path 文件路径
     * @return
     * 私钥文件和keyPair文件是一个的，可以从私钥推导出公钥
     */
    public static PrivateKey readPrivateKeyFromFile(String path) throws Exception {
        return readKeyPairFromFile(path).getPrivate();
    }

    /**
     * 从文件中读取密钥对
     *
     * @param path 文件路径
     * @return
     */
    public static KeyPair readKeyPairFromFile(String path) throws Exception {
        Object obj = readPEM(path);
        return new JcaPEMKeyConverter().setProvider(Constants.provider).getKeyPair((PEMKeyPair) obj);
    }
}
