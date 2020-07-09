package com.ggp.noob.pki.crl;


import com.ggp.noob.common.base.AbstractProvider;
import com.ggp.noob.common.base.Constants;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.CRLNumber;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.cert.X509CRLHolder;
import org.bouncycastle.cert.X509v2CRLBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CRLConverter;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.util.encoders.Base64;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.PrivateKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLEntry;
import java.util.ArrayList;
import java.util.Date;
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
     * 生成crl
     * @param issuer     签发者subject
     * @param issuerKey  签发者私钥
     * @param signAlg    签名算法
     * @param crlNumber  crl数字
     * @param thisUpdate 本次更新时间
     * @param nextUpdate 下次更新时间
     * @param extensions 扩展项
     * @param revoked    被撤销证书信息
     * @return
     * @throws Exception
     */
    public static X509CRL generateX509CRL(X500Name issuer,PrivateKey issuerKey,String signAlg, BigInteger crlNumber, Date thisUpdate, Date nextUpdate, List<Extension> extensions, List<CrlEntry> revoked) throws Exception{
        JcaContentSignerBuilder signerBuilder = new JcaContentSignerBuilder(signAlg);
        ContentSigner contentSigner = signerBuilder.build(issuerKey);
        X509v2CRLBuilder builder = new X509v2CRLBuilder(issuer,thisUpdate);
        builder.addExtension(Extension.cRLNumber, false, new CRLNumber(crlNumber));
        if(null != extensions){
            for(Extension extension:extensions){
                builder.addExtension(extension);
            }
        }
        if(null != revoked){
            for(CrlEntry entry:revoked){
                builder.addCRLEntry(new BigInteger(entry.getSn(),16),entry.getDate(),entry.getReason());
            }
        }
        builder.setNextUpdate(nextUpdate);
        X509CRLHolder holder = builder.build(contentSigner);
        return new JcaX509CRLConverter().getCRL(holder);

    }

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
