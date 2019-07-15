package com.xm.xmstore.service.ex;

/**
 * 	手机号码已注册异常
 * @author Administrator
 *
 */
public class PhoneDuplicateException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public PhoneDuplicateException() {
		super();
	}

	public PhoneDuplicateException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PhoneDuplicateException(String message, Throwable cause) {
		super(message, cause);
	}

	public PhoneDuplicateException(String message) {
		super(message);
	}

	public PhoneDuplicateException(Throwable cause) {
		super(cause);
	}
	
}
