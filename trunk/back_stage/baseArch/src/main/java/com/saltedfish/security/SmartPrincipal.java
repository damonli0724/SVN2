/*******************************************************************************
 * Project   : baseArch
 * Class Name: com.saltedfish.security.SmartPrincipal
 * Created By: mjy 
 * Created on: 2016年1月13日 下午3:50:26
 * Copyright © 2013-2014 YYQ All rights reserved.
 ******************************************************************************/
package com.saltedfish.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.util.Assert;


public class SmartPrincipal {
	private String username;
	private String ip;

	public SmartPrincipal(String username, String ip) {
		Assert.notNull(username, "username cannot be null (violation of interface contract)");
		Assert.notNull(ip, "username cannot be null (violation of interface contract)");
		this.username = username;
		this.ip = ip;
	}

	public SmartPrincipal(Authentication authentication) {
		Assert.notNull(authentication, "authentication cannot be null (violation of interface contract)");

		String username = null;

		if (authentication.getPrincipal() instanceof UserDetails) {
			username = ((UserDetails) authentication.getPrincipal()).getUsername();
		} else {
			username = (String) authentication.getPrincipal();
		}

		String ip = ((WebAuthenticationDetails) authentication.getDetails()).getRemoteAddress();
		this.username = username;
		this.ip = ip;
	}

	public boolean equalsIp(SmartPrincipal smartPrincipal) {
		return this.ip.equals(smartPrincipal.ip);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof SmartPrincipal) {
			SmartPrincipal smartPrincipal = (SmartPrincipal) obj;

			return username.equals(smartPrincipal.username);
		}

		return false;
	}

	@Override
	public int hashCode() {
		return username.hashCode();
	}

	@Override
	public String toString() {
		return "SmartPrincipal:{username=" + username + ",ip=" + ip + "}";
	}
}
