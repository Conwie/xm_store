package com.xm.xmstore.service.ex;

public class CodeErrorException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public CodeErrorException() {
		super();
	}

	public CodeErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CodeErrorException(String message, Throwable cause) {
		super(message, cause);
	}

	public CodeErrorException(String message) {
		super(message);
	}

	public CodeErrorException(Throwable cause) {
		super(cause);
	}

}
