package com.arch.cmd.admin;

import java.util.Date;

import com.arch.cmd.base.BaseCmd;

/**
 * 管理员添加 
 * @author lkd
 *
 */
public class AdminAddCmd {
	private String userName; //用户姓名
	private String name; // 账号
	private String originalPassword;//初始密码
	private String confirmPassword; // 确认密码
	private String mobile; // 电话
	private  Integer sex ; //性别 1 男 0 女
	private String  email; // email
	private String description;//描述
	private String roleId;  //角色Id
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOriginalPassword() {
		return originalPassword;
	}
	public void setOriginalPassword(String originalPassword) {
		this.originalPassword = originalPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	@Override
	public String toString() {
		return "AdminAddCmd [userName=" + userName + ", name=" + name
				+ ", originalPassword=" + originalPassword
				+ ", confirmPassword=" + confirmPassword + ", mobile=" + mobile
				+ ", sex=" + sex + ", email=" + email + ", description="
				+ description + ", roleId=" + roleId + "]";
	}
}
