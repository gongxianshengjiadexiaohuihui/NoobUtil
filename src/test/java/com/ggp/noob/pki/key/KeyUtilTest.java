package com.ggp.noob.pki.key;

import com.ggp.noob.common.base.Constants;
import com.ggp.noob.pki.pem.PemUtil;
import org.junit.Test;

import java.security.KeyPair;

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
    public void test(){
        String temp = "";
        System.out.println(new String(temp.getBytes()));
    }
}