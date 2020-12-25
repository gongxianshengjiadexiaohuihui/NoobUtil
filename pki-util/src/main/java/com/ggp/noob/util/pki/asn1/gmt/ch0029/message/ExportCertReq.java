package com.ggp.noob.util.pki.asn1.gmt.ch0029.message;

import org.bouncycastle.asn1.*;

/**
 * @author ggp
 * @Date 2020/2/20 14:25
 * @Description 导出证书请求包
 */
public class ExportCertReq extends ASN1Object {
    /**
     * 导出证书的标识
     */
    DEROctetString identification;

    public ExportCertReq(ASN1Sequence instance){
        if(instance.size() != 1){
            throw new IllegalArgumentException("wrong size for ExportCertReq!");
        }
        this.identification = (DEROctetString) DEROctetString.getInstance(instance.getObjectAt(0));
    }
    public ExportCertReq(String str){
        this.identification = new DEROctetString(str.getBytes());
    }
    public static ExportCertReq getInstance(Object obj){
        if( obj instanceof ExportCertReq){
            return (ExportCertReq)obj;
        }
        if(null != obj){
            return new ExportCertReq(ASN1Sequence.getInstance(obj));
        }
        throw new IllegalArgumentException("the obj is null!");
    }
    @Override
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector vector = new ASN1EncodableVector();
        vector.add(identification);
        return new DERSequence(vector);
    }

    public DEROctetString getIdentification() {
        return identification;
    }
}
