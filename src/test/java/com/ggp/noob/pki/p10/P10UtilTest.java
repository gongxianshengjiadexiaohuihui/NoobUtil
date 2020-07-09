package com.ggp.noob.pki.p10;

import com.ggp.noob.common.base.Constants;
import com.ggp.noob.common.enums.pki.SignatureAlgorithmEnum;
import com.ggp.noob.pki.key.KeyUtil;
import com.ggp.noob.pki.pem.PemUtil;
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
    public void createP10_nist() throws Exception {
        KeyPair keyPair = KeyUtil.createECDSAKeyPair();
        String dn = "CN=TEST,O=NIST,C=CN";
        X500Name subject = new X500Name(dn);
        PemUtil.writeObjectToFile(keyPair.getPublic(), Constants.ROOT_PATH + "publicKey.dat");
        PKCS10CertificationRequest p10 = P10Util.createP10(subject, SignatureAlgorithmEnum.SHA256_WITH_ECDSA.name, keyPair.getPublic(), keyPair.getPrivate());
        PemUtil.writeObjectToFile(p10, Constants.ROOT_PATH + "test.p10");
    }
    @Test
    public void createP10_sm2() throws Exception {
        KeyPair keyPair = KeyUtil.createSm2KeyPair();
        String dn = "CN=TEST,O=SM2,C=CN";
        X500Name subject = new X500Name(dn);
        PemUtil.writeObjectToFile(keyPair.getPublic(), Constants.ROOT_PATH + "publicKey.dat");
        PKCS10CertificationRequest p10 = P10Util.createP10(subject, SignatureAlgorithmEnum.SM3_WITH_SM2.name, keyPair.getPublic(), keyPair.getPrivate());
        PemUtil.writeObjectToFile(p10, Constants.ROOT_PATH + "test.p10");
    }
    @Test
    public void createP10_rsa() throws Exception {
        KeyPair keyPair = KeyUtil.createRSAKeyPair(1024);
        String dn = "CN=TEST,O=RSA,C=CN";
        X500Name subject = new X500Name(dn);
        PemUtil.writeObjectToFile(keyPair.getPublic(), Constants.ROOT_PATH + "publicKey.dat");
        PKCS10CertificationRequest p10 = P10Util.createP10(subject, SignatureAlgorithmEnum.SHA256_WITH_RSA.name, keyPair.getPublic(), keyPair.getPrivate());
        PemUtil.writeObjectToFile(p10, Constants.ROOT_PATH + "test.p10");
    }
    @Test
    public void getPublicFromP10() throws Exception{
        String p10 ="MIIBszCCARwCAQAwgYIxJTAjBgNVBAMMHOadjumAjemBpSA0NTIxMTIxOTk1MTAyMTQyMjUxCzAJBgNVBAsMAjAwMQswCQYDVQQLDAIwMDELMAkGA1UECgwCMDAxCzAJBgNVBAcMAjAwMQswCQYDVQQHDAIwMDELMAkGA1UECAwCNDExCzAJBgNVBAYTAkNOMIGRMAsGCSqGSIb3DQEBAQOBgQDLS99HnQHpt2f6P1AcbUAG/vJT/EopqcWUAQb7JTHTXCKiYCgBKlvlhnIeHOLUYOHY8sW6epS0EYPxo8cQSvzaOuYjjNVY+B2kZx6UzHM930FCrKgekm+vVluqyh5TU3SFTQJwObHxqhdUmHLNBEzdP6Y7xzJjXzkWDBVOTCCstTANBgkqhkiG9w0BAQUFAAOBgQBSbiSEk7W3a+O64HIiSAMkgTY5Zq637U2eTDrSE7gA7Pb3fxwEQ0we1HKr3jMrZGS6rfl0EjSbDi4pG57BuAEcTs6e09CXRoJmseeWlFSILodjBz8qbKg9NjhDijENptQA/MtEBOXOjDvALAGONHkhAzBHepxVV1Zq4kmFr9hssw==";
        P10Util.getPublicKeyFromP10(p10);

    }

}