package com.ggp.noob.util.pki.asn1.gmt.ch0029.request;

import com.ggp.noob.util.pki.asn1.gmt.ch0029.message.ExportCertReq;
import org.bouncycastle.asn1.*;

/**
 * @author ggp
 * @Date 2020/2/20 13:48
 * @Description
 */
public class Request extends ASN1Object implements ASN1Choice {
    /**
     * 导出证书申请包请求  tag 0
     */
    private ExportCertReq exportCertReq;

    public Request(ASN1TaggedObject obj){
       if(null == obj){
           throw new IllegalArgumentException("obj is null !");
       }
       if(obj.getTagNo() == 0){
           this.exportCertReq = ExportCertReq.getInstance(obj.getObject());
       }else{
           throw new IllegalArgumentException(" don't support for temporary");
       }
    }

    public Request(ExportCertReq req) {
        this.exportCertReq = req;
    }

    public static Request getInstance(Object obj) {
       if(obj instanceof Request){
           return (Request)obj;
       }
       if(null != obj){
           return new Request((ASN1TaggedObject)obj);
       }
       return null;
    }

    @Override
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector vector = new ASN1EncodableVector();
        vector.add(exportCertReq);
        return new DERSequence(vector);
    }
}
