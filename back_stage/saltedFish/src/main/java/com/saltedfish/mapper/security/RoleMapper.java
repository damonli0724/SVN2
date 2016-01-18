package com.saltedfish.mapper.security;

import java.util.List;

import com.saltedfish.entity.security.SysRoles;


public interface RoleMapper {

	/**
	 * 根据用户的ID查询他拥有的角色列表
	 * @param userId
	 * @return
	 */
	public List<SysRoles> selectRolesByUserId(String userId);

	/**
	 * <p>查询所有的角色</p>
	 * @return
	 * @author lkd
	 */
	public List<SysRoles> queryAllRoles();

}
