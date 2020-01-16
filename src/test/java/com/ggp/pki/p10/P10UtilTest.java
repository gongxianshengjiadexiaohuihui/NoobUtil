package com.ggp.pki.p10;

import com.ggp.common.base.Constants;
import com.ggp.common.enums.pki.SignatureAlgorithmEnum;
import com.ggp.pki.key.KeyUtil;
import com.ggp.pki.pem.PemUtil;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.junit.Test;

import java.security.KeyPair;


/**
 * @author: ggp
 * @Date: 2020/1/16 14:13
 * @Description:
 */
public class P10UtilTest {

    @Test
    public void createP10() throws Exception {
        KeyPair keyPair = KeyUtil.createECDSAKeyPair();
        String dn = "CN=TEST,O=NIST,C=CN";
        X500Name subject = new X500Name(dn);
        PemUtil.writeObjectToFile(keyPair.getPublic(), Constants.ROOT_PATH + "publicKey.dat");
        PKCS10CertificationRequest p10 = P10Util.createP10(subject, SignatureAlgorithmEnum.SHA256_WITH_ECDSA.name, keyPair.getPublic(), keyPair.getPrivate());
        PemUtil.writeObjectToFile(p10, Constants.ROOT_PATH + "test.p10");
    }
}