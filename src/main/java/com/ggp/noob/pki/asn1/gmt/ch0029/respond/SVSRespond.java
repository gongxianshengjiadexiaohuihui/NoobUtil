package com.ggp.noob.pki.asn1.gmt.ch0029.respond;

import org.bouncycastle.asn1.*;

/**
 * @author ggp
 * @Date 2020/2/20 15:36
 * @Description
 */
public class SVSRespond extends ASN1Object {
    /**
     * 协议版本 默认是v1 值是0
     */
    private ASN1Integer version;
    /**
     * 响应类型 0-18
     */
    private ASN1Integer respType;
    /**
     * 响应包
     */
    private Respond respond;
    /**
     * 响应时间
     */
    private ASN1GeneralizedTime respTime;

    public SVSRespond(ASN1Sequence instance){
        if(instance.size() != 4){
            throw new IllegalArgumentException("wrong size for SVSRespond!");
        }
        this.version = ASN1Integer.getInstance(instance.getObjectAt(0));
        if(version.getValue().intValue() != 0){
            throw new IllegalArgumentException("wrong version for SVSRespond!");
        }
        this.respType = ASN1Integer.getInstance(instance.getObjectAt(1));
        if(respType.getValue().intValue()>18 || respType.getValue().intValue() < 0){
            throw new IllegalArgumentException("wrong reqType for SVSRespond！reqType:"+respType.getValue().intValue());
        }
        this.respond = Respond.getInstance(instance.getObjectAt(2));
        this.respTime = ASN1GeneralizedTime.getInstance(instance.getObjectAt(3));
    }
    public static SVSRespond getInstance(Object obj){
        if(obj instanceof SVSRespond){
            return (SVSRespond)obj;
        }
        if(null != obj){
            return new SVSRespond(ASN1Sequence.getInstance(obj));
        }
        throw new IllegalArgumentException("the obj is null!");
    }

    public ASN1Integer getVersion() {
        return version;
    }

    public ASN1Integer getRespType() {
        return respType;
    }

    public Respond getRespond() {
        return respond;
    }

    public ASN1GeneralizedTime getRespTime() {
        return respTime;
    }

    @Override
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector vector = new ASN1EncodableVector();
        vector.add(version);
        vector.add(respType);
        vector.add(respond);
        vector.add(respTime);
        return new DERSequence(vector);
    }
}
