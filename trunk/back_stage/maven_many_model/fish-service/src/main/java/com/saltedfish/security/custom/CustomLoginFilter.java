package com.saltedfish.security.custom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

import com.saltedfish.exception.CustomAuthenticationException;


public class CustomLoginFilter extends UsernamePasswordAuthenticationFilter {

	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

		String username = obtainUsername(request);
		String password = obtainPassword(request);

		String requestCaptcha = obtainRequestCaptcha(request);
		String sessionCaptcha = obtainSessionCaptcha(request);

		
		//验证码检测，如果验证码不同过，就不要进行下面操作了
		checkCaptcha(requestCaptcha, sessionCaptcha);
		
		// 这里将原来的UsernamePasswordAuthenticationToken换成我们自定义的CustomAuthenticationToken
		CustomAuthenticationToken authRequest = new CustomAuthenticationToken(username, password);

		// 这里就将token传到后续验证环节了
		setDetails(request, authRequest);
		return this.getAuthenticationManager().authenticate(authRequest);
	}

	protected String obtainRequestCaptcha(HttpServletRequest request) {
		return request.getParameter(getRequestCaptchaParameter());
	}

	protected String obtainSessionCaptcha(HttpServletRequest request) {
		return (String) request.getSession().getAttribute(getSessionCaptchaParameter());
	}

	String requestCaptchaParameter = "code";   // 页面输入的验证码
	String sessionCaptchaParameter = "randomStr";// session生成的验证码

	public String getRequestCaptchaParameter() {
		return requestCaptchaParameter;
	}

	public void setRequestCaptchaParameter(String requestCaptchaParameter) {
		this.requestCaptchaParameter = requestCaptchaParameter;
	}

	public String getSessionCaptchaParameter() {
		return sessionCaptchaParameter;
	}

	public void setSessionCaptchaParameter(String sessionCaptchaParameter) {
		this.sessionCaptchaParameter = sessionCaptchaParameter;
	}

	protected void checkCaptcha(String requestCaptcha,String sessionCaptcha) {
		if (StringUtils.isEmpty(requestCaptcha) || StringUtils.isEmpty(sessionCaptcha)) {
			throw new CustomAuthenticationException("验证码不能为空");
		}

		if (!sessionCaptcha.equalsIgnoreCase(requestCaptcha)) {
			throw new CustomAuthenticationException("验证码不正确");
		}
	}
	
	
}
