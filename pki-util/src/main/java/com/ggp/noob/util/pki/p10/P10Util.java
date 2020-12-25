package com.ggp.noob.util.pki.p10;


import com.ggp.noob.util.pki.common.base.AbstractProvider;
import com.ggp.noob.util.pki.common.base.Constants;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.PKCS10CertificationRequestBuilder;
import org.bouncycastle.pkcs.jcajce.JcaPKCS10CertificationRequestBuilder;
import org.bouncycastle.util.encoders.Base64;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author: ggp
 * @Date: 2020/1/16 11:33
 * @Description:
 */
public class P10Util extends AbstractProvider {
    public static final String P10_HEAD = "-----BEGIN CERTIFICATE REQUEST-----";
    public static final String P10_TAIL = "-----END CERTIFICATE REQUEST-----";

    /**
     * 生成p10
     * @param subject                 证书主体
     * @param signatureAlgorithmName  签名算法
     * @param publicKey               p10中的公钥
     * @param privateKey              p10中的公钥对应的私钥
     * @return
     */
    public static PKCS10CertificationRequest createP10(X500Name subject, String signatureAlgorithmName, PublicKey publicKey, PrivateKey privateKey) throws Exception{
        PKCS10CertificationRequestBuilder builder = new JcaPKCS10CertificationRequestBuilder(subject,publicKey);
        ContentSigner signer = new JcaContentSignerBuilder(signatureAlgorithmName).setProvider(Constants.provider).build(privateKey);
        return builder.build(signer);
    }

    /**
     * 从P10中解析公钥
     * @param p10    base64格式的p10
     * @return
     * @throws Exception
     */
    public static PublicKey getPublicKeyFromP10(String p10) throws Exception{
        p10 = p10.replace(P10_TAIL, "").replace(P10_HEAD, "");
        p10 = p10.replace("\r", "").replace("\n", "");
        p10 = p10.replace("\\r", "").replace("\\n", "");
        PKCS10CertificationRequest re = new PKCS10CertificationRequest(Base64.decode(p10));
        JcaPEMKeyConverter converter = new JcaPEMKeyConverter().setProvider("BC");
        PublicKey publicKey = converter.getPublicKey(re.getSubjectPublicKeyInfo());
        return publicKey;
    }
}
