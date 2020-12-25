package com.ggp.noob.util.pki.asn1.gmt.ch0029.request;

import org.bouncycastle.asn1.*;

import java.util.Date;

/**
 * @author ggp
 * @Date 2020/2/20 13:46
 * @Description
 */
public class SVSRequest extends ASN1Object {
    /**
     * 版本  默认v1 取值0
     */
    private ASN1Integer version;
    /**
     * 请求类型 0-18
     */
    private ASN1Integer reqType;
    /**
     * 请求包
     */
    private Request request;
    /**
     * 请求时间
     */
    private ASN1GeneralizedTime reqTime;
    public SVSRequest(int type,Request request){
        this.version = new ASN1Integer(0);
        this.reqType = new ASN1Integer(type);
        this.request = request;
        this.reqTime = new ASN1GeneralizedTime(new Date());
    }
    public SVSRequest(ASN1Sequence instance){
        if(instance.size() != 4){
            throw new IllegalArgumentException("wrong size for SVSRequest!");
        }
        this.version = ASN1Integer.getInstance(instance.getObjectAt(0));
        if(version.getValue().intValue()!=0){
            throw new IllegalArgumentException("wrong version for SVSRequest!");
        }
        this.reqType = ASN1Integer.getInstance(instance.getObjectAt(1));
        if(reqType.getValue().intValue()>18 || reqType.getValue().intValue() < 0){
            throw new IllegalArgumentException("wrong reqType for SVSRequest！reqType:"+reqType.getValue().intValue());
        }
        this.request = Request.getInstance(instance.getObjectAt(2));
        this.reqTime = ASN1GeneralizedTime.getInstance(instance.getObjectAt(3));
    }
    public static SVSRequest getInstance(Object o){
       if(o instanceof SVSRequest){
           return (SVSRequest)o;
       }
       if(null != o){
           return new SVSRequest(ASN1Sequence.getInstance(o));
       }

       throw new IllegalArgumentException("the object is null!");
    }

    public ASN1Integer getVersion() {
        return version;
    }

    public ASN1Integer getReqType() {
        return reqType;
    }

    public Request getRequest() {
        return request;
    }

    public ASN1GeneralizedTime getReqTime() {
        return reqTime;
    }

    @Override
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector vector = new ASN1EncodableVector();
        vector.add(version);
        vector.add(reqType);
        vector.add(request);
        vector.add(reqTime);
        return new DERSequence(vector);
    }
}
