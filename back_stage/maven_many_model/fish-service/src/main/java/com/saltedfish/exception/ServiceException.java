package com.saltedfish.exception;

/**
 * 业务级别异常
 * @author lkd
 *
 */
public class ServiceException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ServiceException(String message) {
		super(message);
	}
	
}
