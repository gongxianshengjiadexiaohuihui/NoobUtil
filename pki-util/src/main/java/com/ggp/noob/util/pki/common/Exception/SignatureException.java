package com.ggp.noob.util.pki.common.Exception;

/**
 * @author: ggp
 * @Date: 2019/11/6 15:49
 * @Description:
 */
public class SignatureException extends Exception {
    /**
     * 构造函数
     * @param message 异常描述
     */
    public SignatureException(String message) {
        super(message);
    }

    /**
     * 构造函数
     * @param message 异常描述
     * @param cause 接受异常堆栈
     */
    public SignatureException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 构造函数
     * @param e 异常
     */
    public SignatureException(Exception e){
        super(e);
    }
}
