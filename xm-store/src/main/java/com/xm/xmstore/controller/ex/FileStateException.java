package com.xm.xmstore.controller.ex;

/**
 * 保存文件时文件状态异常
 */

public class FileStateException extends FileUploadException {

	private static final long serialVersionUID = -2240259262068074477L;

	public FileStateException() {
		super();
	}

	public FileStateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FileStateException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileStateException(String message) {
		super(message);
	}

	public FileStateException(Throwable cause) {
		super(cause);
	}

}
