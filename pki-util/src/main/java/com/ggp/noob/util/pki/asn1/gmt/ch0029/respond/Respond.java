package com.ggp.noob.util.pki.asn1.gmt.ch0029.respond;


import com.ggp.noob.util.pki.asn1.gmt.ch0029.message.ExportCertResp;
import org.bouncycastle.asn1.*;

/**
 * @author ggp
 * @Date 2020/2/20 15:38
 * @Description
 */
public class Respond extends ASN1Object implements ASN1Choice {
    /**
     * 导出证书响应   tag 0
     */
    private ExportCertResp exportCertResp;
    public Respond(ASN1TaggedObject obj){
        if(null != obj){
            throw new IllegalArgumentException("the obj is null!");
        }
        if(obj.getTagNo() == 0){
            this.exportCertResp = ExportCertResp.getInstance(obj.getObject());
        }else{
            throw new IllegalArgumentException(" don't support for temporary");
        }
    }
    public static Respond getInstance(Object obj) {
        if(obj instanceof Respond){
            return (Respond)obj;
        }
        if(null != obj){
            return new Respond((ASN1TaggedObject)obj);
        }
        return null;
    }

    public ExportCertResp getExportCertResp() {
        return exportCertResp;
    }

    @Override
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector vector = new ASN1EncodableVector();
        vector.add(exportCertResp);
        return new DERSequence(vector);
    }
}
