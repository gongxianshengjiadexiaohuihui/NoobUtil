package com.ggp.common.enums.pki;

/**
 * @author: ggp
 * @Date: 2019/11/6 15:32
 * @Description:签名算法枚举类
 */
public enum SignatureAlgorithmEnum {
    SM3_WITH_SM2("1.2.156.10197.1.501", "SM3WithSM2"),
    SHA1_WITH_RSA("1.2.840.113549.1.1.5", "SHA1WithRSA"),
    SHA256_WITH_RSA("1.2.840.113549.1.1.11", "SHA256WithRSA");
    public String oid;
    public String name;

    SignatureAlgorithmEnum(String oid, String name) {
        this.oid = oid;
        this.name = name;
    }
}
