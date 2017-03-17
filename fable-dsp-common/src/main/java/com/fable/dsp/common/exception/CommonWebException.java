package com.fable.dsp.common.exception;

/**
 * WEB层的异常通用类
 * 
 * @author 汪朝
 */
public class CommonWebException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 3344088763231997499L;

    public CommonWebException() {
        super();
    }

    public CommonWebException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommonWebException(String message) {
        super(message);
    }

    public CommonWebException(Throwable cause) {
        super(cause);
    }

}
