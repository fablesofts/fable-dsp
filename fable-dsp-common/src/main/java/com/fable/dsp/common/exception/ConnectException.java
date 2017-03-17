package com.fable.dsp.common.exception;

/**
 * 数据库连接异常.
 * @author zhangl
 *
 */
public class ConnectException extends RuntimeException {


    private static final long serialVersionUID = -702041436416753479L;

    public ConnectException() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ConnectException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public ConnectException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public ConnectException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }
    
    

}
