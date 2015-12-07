package com.arch.mapper.securtity;

import java.util.List;

import com.arch.entity.SysRoles;
import com.arch.entity.SysUsers;



public interface RoleMapper {


	/**
	 * 根据用户的ID查询他拥有的角色列表
	 * @param userId
	 * @return
	 */
	public List<SysRoles> selectRolesByUserId(String userId);

}
