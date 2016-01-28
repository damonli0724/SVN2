package com.saltedfish.exception;

import org.springframework.security.core.AuthenticationException;


public class CustomAuthenticationException extends AuthenticationException {

	private static final long serialVersionUID = -3333012976129153127L;

	/**
	 * <p>自定义认证异常</p>
	 */
	public CustomAuthenticationException(String msg) {
		super(msg);
	}

}
