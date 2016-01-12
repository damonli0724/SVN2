package com.saltedfish.dto.security;

import com.saltedfish.entity.security.SysUsers;

/**
 * 
 * 管理员列表查询DTO
 * @author LKD
 *
 */
public class UserListDTO extends SysUsers{
	private String roleName;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
