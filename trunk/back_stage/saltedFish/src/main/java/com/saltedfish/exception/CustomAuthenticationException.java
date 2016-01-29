package com.saltedfish.exception;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;


public class CustomAuthenticationException extends AuthenticationException {

	private static final long serialVersionUID = -3333012976129153127L;
	private MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

	/**
	 * <p>自定义认证异常</p>
	 */
	public CustomAuthenticationException(String msg) {
		super(msg);
	}

}
