package com.tpay.common.exceptions;

/**
 * @author tuyong
 * @version 1.0
 * @DESCRIPTION  业务层异常
 * @create 2017-07-07 11:06
 **/
public class BusinessException extends BaseException{
    public BusinessException() {
    }
    public BusinessException(Throwable ex) {
        super(ex);
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable ex) {
        super(message, ex);
    }
}
