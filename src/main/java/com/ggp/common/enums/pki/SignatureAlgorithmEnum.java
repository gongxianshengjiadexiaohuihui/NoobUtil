package com.ggp.common.enums.pki;

/**
 * @author: ggp
 * @Date: 2019/11/6 15:32
 * @Description:签名算法枚举类
 */
public enum SignatureAlgorithmEnum {
    SM3_WITH_SM2("1.2.156.10197.1.501", "SM3WithSM2"),
    SHA1_WITH_RSA("1.2.840.113549.1.1.5", "SHA1WithRSA"),
    SHA256_WITH_RSA("1.2.840.113549.1.1.11", "SHA256WithRSA"),
    SHA256_WITH_ECDSA("1.2.840.10045.4.3.2","SHA256WithECDSA");
    public String oid;
    public String name;

    SignatureAlgorithmEnum(String oid, String name) {
        this.oid = oid;
        this.name = name;
    }

    /**
     * 判断是否支持该签名算法
     * @param name
     * @return
     */
    public static boolean contain(String name){
        for(SignatureAlgorithmEnum signatureAlgorithmEnum:SignatureAlgorithmEnum.values()){
            if(signatureAlgorithmEnum.name.equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }
}
