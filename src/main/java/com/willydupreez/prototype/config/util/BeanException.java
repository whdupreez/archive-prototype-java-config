package com.willydupreez.prototype.config.util;

public class BeanException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BeanException(String message, Throwable cause) {
		super(message, cause);
	}

	public BeanException(String message) {
		super(message);
	}

}
