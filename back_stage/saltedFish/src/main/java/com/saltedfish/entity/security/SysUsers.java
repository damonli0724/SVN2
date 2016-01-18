/*******************************************************************************
 * Project   : saltedfishbase
 * Class Name: com.saltedfish.entity.security.SysUsers
 * Created By: mjy 
 * Created on: 2015年11月27日 上午10:16:08
 * Copyright © 2013-2014 YYQ All rights reserved.
 ******************************************************************************/
package com.saltedfish.entity.security;

import java.util.Date;


/**
 * <P>TODO</P>
 * @author lkd
 */
public class SysUsers {

	private String userId;
	private String username;
	private String name;
	private String password;
	private Date dtCreate;
	private Date lastLogin;
	private Date deadline;
	private String loginIp;
	private String VQzjgid;
	private String VQzjgmc;
	private String depId;
	private String depName;
	
	private String email;
	private String mobile;
	private String description;
	private Integer  sex;
	
	private boolean enabled;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDtCreate() {
		return dtCreate;
	}

	public void setDtCreate(Date dtCreate) {
		this.dtCreate = dtCreate;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getVQzjgid() {
		return VQzjgid;
	}

	public void setVQzjgid(String vQzjgid) {
		VQzjgid = vQzjgid;
	}

	public String getVQzjgmc() {
		return VQzjgmc;
	}

	public void setVQzjgmc(String vQzjgmc) {
		VQzjgmc = vQzjgmc;
	}

	public String getDepId() {
		return depId;
	}

	public void setDepId(String depId) {
		this.depId = depId;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	@Override
	public String toString() {
		return "SysUsers [userId=" + userId + ", username=" + username
				+ ", name=" + name + ", password=" + password + ", dtCreate="
				+ dtCreate + ", lastLogin=" + lastLogin + ", deadline="
				+ deadline + ", loginIp=" + loginIp + ", VQzjgid=" + VQzjgid
				+ ", VQzjgmc=" + VQzjgmc + ", depId=" + depId + ", depName="
				+ depName + ", enabled=" + enabled + ", accountNonExpired="
				+ accountNonExpired + ", accountNonLocked=" + accountNonLocked
				+ ", credentialsNonExpired=" + credentialsNonExpired + "]";
	}
	

}
