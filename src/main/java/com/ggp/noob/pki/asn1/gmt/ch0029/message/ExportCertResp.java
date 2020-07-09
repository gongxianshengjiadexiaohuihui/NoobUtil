package com.ggp.noob.pki.asn1.gmt.ch0029.message;

import org.bouncycastle.asn1.*;
import org.bouncycastle.asn1.x509.Certificate;

/**
 * @author ggp
 * @Date 2020/2/20 14:36
 * @Description
 */
public class ExportCertResp extends ASN1Object {
    /**
     * 响应码，0成功，非0错误
     */
    private ASN1Integer respValue;
    /**
     * 导出的证书  (可选)
     */
    private Certificate cert;
    public ExportCertResp(ASN1Sequence instance){
        if(instance.size() != 1 || instance.size() != 2){
            throw new IllegalArgumentException("wrong size for ExportCertResp!");
        }
        this.respValue = ASN1Integer.getInstance(instance.getObjectAt(0));
        if(instance.size() == 2){
            this.cert = Certificate.getInstance(instance.getObjectAt(1));
        }
    }
    public static ExportCertResp getInstance(Object obj){
        if(obj instanceof ExportCertResp){
            return (ExportCertResp)obj;
        }
        if(null != obj){
            return new ExportCertResp(ASN1Sequence.getInstance(obj));
        }
        throw new IllegalArgumentException("the obj is null!");
    }
    public ASN1Integer getRespValue() {
        return respValue;
    }

    public Certificate getCert() {
        return cert;
    }

    @Override
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector vector = new ASN1EncodableVector();
        vector.add(respValue);
        if(null != cert){
            vector.add(cert);
        }
        return new DERSequence(vector);
    }
}
