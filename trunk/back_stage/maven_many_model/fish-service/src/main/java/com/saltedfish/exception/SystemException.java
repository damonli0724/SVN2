package com.saltedfish.exception;

/**
 * 系统级别异常
 * @author lkd
 *
 */
public class SystemException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public SystemException(String message) {
		super(message);
	}
	
}
