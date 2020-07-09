package com.ggp.noob.pki.p10;

import com.ggp.noob.common.base.AbstractProvider;
import com.ggp.noob.common.base.Constants;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.PKCS10CertificationRequestBuilder;
import org.bouncycastle.pkcs.jcajce.JcaPKCS10CertificationRequestBuilder;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author: ggp
 * @Date: 2020/1/16 11:33
 * @Description:
 */
public class P10Util extends AbstractProvider {
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
}
