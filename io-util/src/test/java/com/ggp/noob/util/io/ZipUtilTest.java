package com.ggp.noob.util.io;


import com.ggp.noob.util.io.zip.ZipUtil;
import com.ggp.noob.util.pki.common.base.Constants;
import com.ggp.noob.util.pki.common.enums.pki.SignatureAlgorithmEnum;
import com.ggp.noob.util.pki.key.KeyUtil;
import com.ggp.noob.util.pki.p10.P10Util;
import com.ggp.noob.util.pki.pem.PemUtil;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.util.encoders.Base64;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;


/**
 * @author: ggp
 * @Date: 2020/3/9 15:54
 * @Description:
 */
public class ZipUtilTest {
    @Test
    public void should_create_zip() throws Exception{
        KeyPair keyPair = KeyUtil.createRSAKeyPair(2048);
        String dn = "CN=TEST,O=SM2,C=CN";
        X500Name subject = new X500Name(dn);
        PemUtil.writeObjectToFile(keyPair.getPublic(), Constants.ROOT_PATH + "publicKey.dat");
        PKCS10CertificationRequest p10 = P10Util.createP10(subject, SignatureAlgorithmEnum.SHA256_WITH_RSA.name, keyPair.getPublic(), keyPair.getPrivate());
        Map<String, byte[]> files = new HashMap<>();
        files.put("km.p10", Base64.encode(p10.getEncoded()));
        files.put("km.dat", Base64.encode(keyPair.getPublic().getEncoded()));
        byte[] zip = ZipUtil.buildZip(files);
        File file = new File(Constants.ROOT_PATH+"test.zip");

        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(zip);
        fileOutputStream.flush();
        fileOutputStream.close();
    }

}