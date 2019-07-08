package com.xm.xmstore.controller.ex;

/**
 * 保存文件时读取异常
 */
public class FileUploadIOException extends FileUploadException {

	private static final long serialVersionUID = 818529674298249650L;

	public FileUploadIOException() {
		super();
	}

	public FileUploadIOException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FileUploadIOException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileUploadIOException(String message) {
		super(message);
	}

	public FileUploadIOException(Throwable cause) {
		super(cause);
	}

}
