/*******************************************************************************
 * Project   : saltedFish
 * Class Name: com.saltedfish.cmd.admin.RoleAddCmd
 * Created By: mjy 
 * Created on: 2016年1月19日 上午11:34:55
 * Copyright © 2013-2014 YYQ All rights reserved.
 ******************************************************************************/
package com.saltedfish.cmd.admin;

import java.util.Arrays;


/**
 * <P>角色添加数据</P>
 * @author lkd
 */
public class RoleAddCmd {

	private String roleName; // 角色名称
	private String roleDesc; // 角色描述
	private String resources; // 权限集合 以逗号分隔

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getResources() {
		return resources;
	}

	public void setResources(String resources) {
		this.resources = resources;
	}

	/**
	 * 
	 * <p>获取权限数组</p>
	 * @return
	 * @author lkd
	 */
	public String[] getRes() {
		return resources.split(",");
	}

	

}
