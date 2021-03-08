package com.ggp.noob.util.pki.p10;

import com.ggp.noob.util.pki.common.base.Constants;
import com.ggp.noob.util.pki.common.enums.pki.SignatureAlgorithmEnum;
import com.ggp.noob.util.pki.key.KeyUtil;
import com.ggp.noob.util.pki.pem.PemUtil;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.util.encoders.Base64;
import org.junit.Test;

import java.security.KeyPair;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.RSAPublicKey;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


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
        PemUtil.writeObjectToFile(keyPair.getPublic(), Constants.ROOT_PATH + "nist_publicKey.dat");
        PKCS10CertificationRequest p10 = P10Util.createP10(subject, SignatureAlgorithmEnum.SHA256_WITH_ECDSA.name, keyPair.getPublic(), keyPair.getPrivate());
        System.out.println(Base64.toBase64String(p10.getEncoded()));
        //PemUtil.writeObjectToFile(p10, Constants.ROOT_PATH + "nist.p10");
    }
    @Test
    public void createP10_sm2() throws Exception {
        KeyPair keyPair = KeyUtil.createSm2KeyPair();
        String dn = "CN=TEST,O=XDJA,C=CN";
        X500Name subject = new X500Name(dn);
        PemUtil.writeObjectToFile(keyPair.getPublic(), Constants.ROOT_PATH + "sm2_publicKey.dat");
        PemUtil.writeObjectToFile(keyPair.getPrivate(),Constants.ROOT_PATH+"sm2_private.dat");
        PKCS10CertificationRequest p10 = P10Util.createP10(subject, SignatureAlgorithmEnum.SM3_WITH_SM2.name, keyPair.getPublic(), keyPair.getPrivate());
        ASN1ObjectIdentifier a = (ASN1ObjectIdentifier)p10.getSubjectPublicKeyInfo().getAlgorithm().getParameters();
        PemUtil.writeObjectToFile(p10, Constants.ROOT_PATH + "sm2.p10");
    }
    @Test
    public void createP10_rsa() throws Exception {
        KeyPair keyPair = KeyUtil.createRSAKeyPair(1024);
        String dn = "CN=TEST,O=RSA,C=CN";
        X500Name subject = new X500Name(dn);
        PemUtil.writeObjectToFile(keyPair.getPublic(), Constants.ROOT_PATH + "rsa_publicKey.dat");
        PKCS10CertificationRequest p10 = P10Util.createP10(subject, SignatureAlgorithmEnum.SHA256_WITH_RSA.name, keyPair.getPublic(), keyPair.getPrivate());
        ASN1Encodable a = p10.getSubjectPublicKeyInfo().getAlgorithm().getParameters();
        System.out.println(a.toString() == "NULL");
        PemUtil.writeObjectToFile(p10, Constants.ROOT_PATH + "rsa.p10");
    }
    @Test
    public void getPublicFromP10() throws Exception{
        String p10 ="-----BEGIN CERTIFICATE REQUEST-----\n" +
                "MIIBaTCB0wIBADAqMQ0wCwYDVQQDDARURVNUMQwwCgYDVQQKDANSU0ExCzAJBgNV\n" +
                "BAYTAkNOMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDrMK3CsSj1iAW/UG2E\n" +
                "EzrpZu+LjrkWF80qpyTICVN6yRyCEQ1kXtc+lGZovny/b2UTqXeRdY5ZSCFFKgKR\n" +
                "1u5T5XP945uMUcUP3FG9fGX/SQWzNtJHm/mq4rAjQ+n4yx8T8BoXlJ95fkxHA0jm\n" +
                "xLpkRresGesewvlvxy0cD03UUwIDAQABoAAwDQYJKoZIhvcNAQELBQADgYEAvz76\n" +
                "7O4i/v4LRsCXgPqKAegPxzYWsgh2UNp7W0PoLPqzR/5y+Q7sBJZH7Gu4mfBjoZ2W\n" +
                "4tkOtV/xeAeSJkU6AoiffrDYJEb6MJFjdMSrTg+skSRV/M/RRolV1Svhu1kuhfUU\n" +
                "rssyuAm0iieovJ+KLJX0Z4W0ey8W5QyLp7Kc12o=\n" +
                "-----END CERTIFICATE REQUEST-----";
        RSAPublicKey publicKey = (RSAPublicKey)P10Util.getPublicKeyFromP10(p10);

        System.out.println(publicKey.getModulus().bitLength());
        System.out.println(publicKey.getAlgorithm());
    }
    @Test
    public void test_getEC_publicFromP10()throws Exception{
        String p10 = "MIHmMIGNAgEAMCsxDTALBgNVBAMMBFRFU1QxDTALBgNVBAoMBE5JU1QxCzAJBgNVBAYTAkNOMFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEQGVhYivi2LFd/ZFUMQWKHjzf+oxG8QHo2crb+reTHJp/Rm2ilPE8EzFzPE8M1ceWN5cpE2NX/tJRqQ8BU/lMfaAAMAoGCCqGSM49BAMCA0gAMEUCIE/jQw97UBBGLp0A4OYWejIcGSG6ZU0mMi+ZxzXG2VrZAiEAoHkLDpSPA6+m8N/Tw71g7DWJ+rErsZPCnqkIRwy/eUI=";
        ECPublicKey publicKey = (ECPublicKey)P10Util.getPublicKeyFromP10(p10);
        System.out.println(publicKey.getW());
    }
    @Test
    public void test_wait() throws Exception{
        ReentrantLock lock = new ReentrantLock();
        Condition test = lock.newCondition();

        Thread t1 = new Thread(()->{
                lock.lock();
        },"T1");
        t1.start();
        Thread.sleep(100);
        Thread t2 =  new Thread(()->{
            lock.lock();
            try {
                test.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"T2");
        t2.start();
        Thread.sleep(100);
        System.out.println(t1.getState());
        System.out.println(t2.getState());
        test.signalAll();
        Thread.sleep(100);
        System.out.println(t1.getState());
        System.out.println(t2.getState());
    }
    @Test
    public void test_sync()throws Exception{
        Object o = new Object();
        Thread t1 = new Thread(()->{
            synchronized (o){
                while (true){

                }
            }
        },"T1");
        t1.start();
        Thread.sleep(100);
        Thread t2 =  new Thread(()->{
            synchronized (o){
                System.out.println("拿到锁了");
            }
        },"T2");
        t2.start();
        Thread.sleep(100);
        System.out.println(t1.getState());
        System.out.println(t2.getState());
        o.notifyAll();
        System.out.println(t1.getState());
        System.out.println(t2.getState());
    }

}