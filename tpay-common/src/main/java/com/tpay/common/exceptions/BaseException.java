package com.tpay.common.exceptions;


/**
 * @desc 异常基类
 * @author tuyong
 * @since 2017/6/15
 * @version 1.0
 */
@SuppressWarnings("serial")
public abstract class BaseException extends RuntimeException {
	public BaseException() {
	}

	public BaseException(Throwable ex) {
		super(ex);
	}

	public BaseException(String message) {
		super(message);
	}

	public BaseException(String message, Throwable ex) {
		super(message, ex);
	}

}
