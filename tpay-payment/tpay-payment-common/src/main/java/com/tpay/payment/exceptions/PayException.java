package com.tpay.payment.exceptions;

import com.tpay.common.exceptions.BaseException;

/**
 * @author tuyong
 * @date 2017年2月6日
 * @desc 支付业务异常
 */
@SuppressWarnings("serial")
public class PayException extends BaseException{
	
	 public PayException(){
	 }

	 public PayException(String message, Throwable cause) {
			super("系统提示: " + message, cause);
	 }

	 public PayException(String message) {
		super("系统提示: " + message);
	 }

	 public PayException(Throwable cause) {
		super(cause);
	 }
}
