package com.saltedfish.security.custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import com.saltedfish.exception.CustomAuthenticationException;
import com.saltedfish.security.MyUserDetailServiceImpl;


public class CustomAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	@Autowired
	private MyUserDetailServiceImpl myUserDetailServiceImpl;

	protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		// 转换为自定义的token
		CustomAuthenticationToken token = (CustomAuthenticationToken) authentication;
		String requestCaptcha = token.getRequestCaptcha();
		String sessionCaptcha = token.getSessionCaptcha();
		// 校验验证码
		if (StringUtils.isEmpty(requestCaptcha) || StringUtils.isEmpty(sessionCaptcha)) {
			throw new CustomAuthenticationException(messages.getMessage("validateCode.isEmpty", "验证码不能为空"));
		}

		if (!sessionCaptcha.equalsIgnoreCase(requestCaptcha)) {
			throw new CustomAuthenticationException(messages.getMessage("validateCode.notEquals", "验证码不正确"));
		}
	}

	/**
	 * 返回UserDetails
	 */
	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		CustomAuthenticationToken custAuthentication = (CustomAuthenticationToken) authentication;

		UserDetails loadedUser = myUserDetailServiceImpl.loadUserByUsername(username);
		if (loadedUser == null) {
			throw new AuthenticationServiceException("UserDetailsService returned null, which is an interface contract violation");
		}
		System.err.println(authentication.getCredentials() + "==============");
		System.err.println(authentication.getAuthorities() + "==============");
		System.err.println(authentication.getDetails() + "==============");
		System.err.println(authentication.getAuthorities() + "==============");
		// PasswordEncodeUtils.encode(custAuthentication.get, authentication.getName(),
		// Constants.PASSWORD_KEY);

		return loadedUser;
	}

}
