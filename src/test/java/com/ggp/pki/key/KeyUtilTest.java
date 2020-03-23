package com.ggp.pki.key;

import com.ggp.common.base.Constants;
import com.ggp.pki.pem.PemUtil;
import org.junit.Test;

import java.security.KeyPair;

/**
 * @author: ggp
 * @Date: 2020/3/9 12:03
 * @Description:
 */
public class KeyUtilTest {
    @Test
    public void should_create_sm2_key() throws Exception{
        KeyPair keyPair = KeyUtil.createSm2KeyPair();

        PemUtil.writeObjectToFile(keyPair.getPrivate(), Constants.ROOT_PATH+"sm2.private");
        PemUtil.writeObjectToFile(keyPair.getPublic(), Constants.ROOT_PATH+"sm2.public");
        PemUtil.writeObjectToFile(keyPair, Constants.ROOT_PATH+"sm2.keyPair");
    }
    @Test
    public void should_read_sm2_key() throws Exception{
        System.out.println(KeyUtil.readPrivateKeyFromFile(Constants.ROOT_PATH+"sm2.private"));
        System.out.println(KeyUtil.readPublicKeyFromFile(Constants.ROOT_PATH+"sm2.public"));
        System.out.println(KeyUtil.readKeyPairFromFile(Constants.ROOT_PATH+"sm2.keyPair").getPublic());
        System.out.println(KeyUtil.readKeyPairFromFile("C:\\Users\\admin\\Desktop\\"+"km.private").getPublic());

    }


}