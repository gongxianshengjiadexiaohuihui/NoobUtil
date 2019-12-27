package com.ggp.common.base;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;

/**
 * @author: ggp
 * @Date: 2019/11/6 16:09
 * @Description:
 */
public abstract class AbstractProvider {
    static {
        if(null == Security.getProvider(BouncyCastleProvider.PROVIDER_NAME)){
            Security.addProvider(new BouncyCastleProvider());
        }
    }
}
