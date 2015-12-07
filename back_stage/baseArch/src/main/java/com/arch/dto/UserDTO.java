package com.arch.dto;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.arch.entity.SysUsers;


/**
  *  <P> 用户登录DTO</P>
  *
  * @author lkd
  * @date  2015/11/27
 */
public class UserDTO extends SysUsers   implements UserDetails, CredentialsContainer{

	private String loginName;

	private Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
	
	
	@Override
	public void eraseCredentials() {
		
	}


	@Override
	public String getPassword() {
		return super.getPassword();
	}

	@Override
	public String getUsername() { //获取密码
		return super.getName();
	}

	@Override
	public boolean isAccountNonExpired() {//
		return super.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() { //是否被锁
		return super.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return super.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {//是否有效
		return super.isEnabled();
	}

	public String getLoginName() {//获取登陆名称 
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public void setAuthorities(Collection<GrantedAuthority> authorities) { 
		this.authorities = authorities;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() { //获取权限列表
		return authorities;
	}
	
	
}
