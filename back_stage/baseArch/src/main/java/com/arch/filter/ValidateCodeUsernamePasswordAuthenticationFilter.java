package com.arch.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

public class ValidateCodeUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	
	/**
	 * 重写attemptAuthentication  进行校验验证码的操作
	 * 
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,HttpServletResponse response) throws AuthenticationException {
		String requestCaptcha = request.getParameter("code");  
		String sessionCaptcha = (String)request.getSession().getAttribute("randomStr"); 
		//当前台输入的验证码为空，或者session中的验证码不等于前台输入的验证码
		if (StringUtils.isEmpty(requestCaptcha)) {  
			throw new  AuthenticationServiceException("validateCode.isEmpty");
		}
		
		if (!sessionCaptcha.equalsIgnoreCase(requestCaptcha)) {
			throw new  AuthenticationServiceException("validateCode.notEquals");
		}
		return super.attemptAuthentication(request, response);
	}

	
}
