package com.saltedfish.dto;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.saltedfish.entity.security.SysUsers;

/**
 * 用户登录DTO
 * @author Administrator
 *
 */
public class UserDTO extends SysUsers   implements UserDetails, CredentialsContainer{

	private static final long serialVersionUID = 1L;

	private String loginName;

	private Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

	public void eraseCredentials() {
		
	}

	public Collection<GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	public String getPassword() {
		return super.getPassword();
	}

	public String getUsername() {
		return super.getName();
	}

	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return super.isAccountNonExpired();
	}

	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return super.isAccountNonLocked();
	}

	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return super.isCredentialsNonExpired();
	}

	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return super.isEnabled();
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	

	
}