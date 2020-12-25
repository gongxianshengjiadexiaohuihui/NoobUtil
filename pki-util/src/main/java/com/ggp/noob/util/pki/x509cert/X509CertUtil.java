package com.ggp.noob.util.pki.x509cert;



import com.ggp.noob.util.pki.common.base.AbstractProvider;
import com.ggp.noob.util.pki.common.base.Constants;
import com.ggp.noob.util.pki.pem.PemUtil;
import org.bouncycastle.asn1.x509.Certificate;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.jce.provider.X509CertificateObject;
import org.bouncycastle.util.encoders.Base64;

import java.io.ByteArrayInputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;


/**
 * @author: ggp
 * @Date: 2020/3/12 08:50
 * @Description:
 */
public class X509CertUtil extends AbstractProvider {
    public static final String BEGIN = "-----BEGIN CERTIFICATE-----";
    public static final String END = "-----END CERTIFICATE-----";

    /**
     * 从base64串解析证书
     *
     * @param base64Str 证书base64格式
     * @return
     */
    public static X509Certificate getCertFromBase64Str(String base64Str) throws Exception {
        if(base64Str.contains(BEGIN)){
            base64Str = base64Str.replace(BEGIN,"");
        }
        if(base64Str.contains(END)){
            base64Str = base64Str.replace(END,"");
        }
        CertificateFactory factory = CertificateFactory.getInstance("x509", Constants.provider);
        return (X509Certificate) factory.generateCertificate(new ByteArrayInputStream(Base64.decode(base64Str)));
    }
    public static X509Certificate getCertFromFile(String path) throws Exception{
        X509CertificateHolder holder = (X509CertificateHolder) PemUtil.readPEM(path);
        return new X509CertificateObject(Certificate.getInstance(holder.getEncoded()));
    }
}
