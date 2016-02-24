package com.saltedfish.security.custom;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


/**
 * 
 * <P>自定义CustomAuthenticationToken  可以添加自定义属性，到后面CustomAuthenticationProvider.additionalAuthenticationChecks 可以进行校验</P>
 * @author LKD
 */
public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomAuthenticationToken(Object principal, Object credentials) {
		super(principal, credentials);
		// TODO Auto-generated constructor stub
	}

	/*private static final long serialVersionUID = 5414106440823275021L;

	public CustomAuthenticationToken(String principal, String credentials, String requestCaptcha, String sessionCaptcha) {
		super(principal, credentials);
		this.requestCaptcha = requestCaptcha;
		this.sessionCaptcha = sessionCaptcha;
	}

	private String requestCaptcha;  // 输入的验证码
	private String sessionCaptcha;  // session生成的验证码

	public String getRequestCaptcha() {
		return requestCaptcha;
	}

	public void setRequestCaptcha(String requestCaptcha) {
		this.requestCaptcha = requestCaptcha;
	}

	public String getSessionCaptcha() {
		return sessionCaptcha;
	}

	public void setSessionCaptcha(String sessionCaptcha) {
		this.sessionCaptcha = sessionCaptcha;
	}*/

}
