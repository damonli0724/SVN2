package com.saltedfish.security.custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import com.saltedfish.exception.CustomAuthenticationException;
import com.saltedfish.security.MyUserDetailServiceImpl;


public class CustomAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	@Autowired
	private MyUserDetailServiceImpl myUserDetailServiceImpl;

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		// 转换为自定义的token
		CustomAuthenticationToken token = (CustomAuthenticationToken) authentication;
		String requestCaptcha = token.getRequestCaptcha();
		String sessionCaptcha = token.getSessionCaptcha();
		// 校验验证码
		if (StringUtils.isEmpty(requestCaptcha) || StringUtils.isEmpty(sessionCaptcha)) {
			throw new CustomAuthenticationException("the captcha can't be null!");
		}

		if (!sessionCaptcha.equalsIgnoreCase(requestCaptcha)) {
			throw new CustomAuthenticationException("the captcha is not matched!");
		}

	}

	/**
	 * 返回UserDetails
	 */
	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

		UserDetails loadedUser = myUserDetailServiceImpl.loadUserByUsername(username);

		if (loadedUser == null) {
			throw new AuthenticationServiceException("UserDetailsService returned null, which is an interface contract violation");
		}
		return loadedUser;
	}

}
