package com.ggp.pki.crl;


import com.ggp.common.base.AbstractProvider;
import com.ggp.common.base.Constants;
import org.bouncycastle.util.encoders.Base64;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author: ggp
 * @Date: 2020/1/11 16:23
 * @Description:
 */
public class CrlUtil extends AbstractProvider {

    /**
     * crl 头
     */
    public static final String BEGIN = "-----BEGIN X509 CRL-----";
    /**
     * crl 尾
     */
    public static final String END = "-----END X509 CRL-----";

    /**
     * 通过base64串获取吊销列表
     * @param base64Str base64串
     * @return
     */
    public static X509CRL getX509CRLByBase64(String base64Str) throws Exception{
        if(base64Str.contains(BEGIN)){
            base64Str = base64Str.replace(BEGIN,"");
        }
        if(base64Str.contains(END)){
            base64Str = base64Str.replace(END,"");
        }
        CertificateFactory factory = CertificateFactory.getInstance("X.509", Constants.provider);
        InputStream inputStream = new ByteArrayInputStream(Base64.decode(base64Str));
        return (X509CRL)factory.generateCRL(inputStream);
    }
    /**
     * 通过CRL获取撤销证书sn列表
     * @param x509CRL  吊销列表
     * @return
     */
    public static List<String> getRevokeCertSnList(X509CRL x509CRL){
        List<String> snList = new ArrayList<String>();
        Set revokedCertificates = x509CRL.getRevokedCertificates();

        for(Object o: revokedCertificates){
            X509CRLEntry x509CRLEntry = (X509CRLEntry)o;
            snList.add(x509CRLEntry.getSerialNumber().toString(16));
        }
        return snList;
    }
}
