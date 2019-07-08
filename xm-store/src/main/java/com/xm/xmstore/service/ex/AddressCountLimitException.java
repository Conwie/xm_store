package com.xm.xmstore.service.ex;

public class AddressCountLimitException extends ServiceException {

	private static final long serialVersionUID = 2813537467949965103L;

	public AddressCountLimitException() {
		super();
	}

	public AddressCountLimitException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AddressCountLimitException(String message, Throwable cause) {
		super(message, cause);
	}

	public AddressCountLimitException(String message) {
		super(message);
	}

	public AddressCountLimitException(Throwable cause) {
		super(cause);
	}
	
	
}
