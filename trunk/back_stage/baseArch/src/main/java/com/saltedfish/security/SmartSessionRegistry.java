/*******************************************************************************
 * Project   : baseArch
 * Class Name: com.saltedfish.security.SmartSessionRegistry
 * Created By: lkd 
 * Created on: 2016年1月13日 下午3:49:35
 * Copyright © 2013-2014 YYQ All rights reserved.
 ******************************************************************************/
package com.saltedfish.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistryImpl;


/**
 * <P>session策略</P>
 * @author lkd
 */
public class SmartSessionRegistry extends SessionRegistryImpl {
	public synchronized void registerNewSession(String sessionId, Object principal) {
		//
		// convert for SmartPrincipal
		//
		if (!(principal instanceof SmartPrincipal)) {
			principal = new SmartPrincipal(SecurityContextHolder.getContext().getAuthentication());
		}

		// FIXME: rememberMe cause sessionId==null, won't success register
		if (sessionId != null) {
			super.registerNewSession(sessionId, principal);
		}
	}

}
