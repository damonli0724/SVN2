package com.saltedfish.security.custom.session;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistryImpl;

/**
 * @description 
 * @author aokunsang
 * @date 2013-9-18
 */
public class CustomSessionRegistryImpl extends SessionRegistryImpl {

	public synchronized void registerNewSession(String sessionId,
	        Object principal) {
		if(!(principal instanceof CustomPrincipal)){
			principal = new CustomPrincipal(SecurityContextHolder.getContext().getAuthentication());
		}
		super.registerNewSession(sessionId, principal);
	}
	
	
}
