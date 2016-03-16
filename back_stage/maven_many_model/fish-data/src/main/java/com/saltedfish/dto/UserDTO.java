package com.saltedfish.dto;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.saltedfish.entity.security.SysUsers;

/**
 * 认证后从数据库返回的DTO
 * @author LKD
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((authorities == null) ? 0 : authorities.hashCode());
		result = prime * result
				+ ((loginName == null) ? 0 : loginName.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		if (authorities == null) {
			if (other.authorities != null)
				return false;
		} else if (!authorities.equals(other.authorities))
			return false;
		if (loginName == null) {
			if (other.loginName != null)
				return false;
		} else if (!loginName.equals(other.loginName))
			return false;
		return true;
	}
	
	
	

	
}