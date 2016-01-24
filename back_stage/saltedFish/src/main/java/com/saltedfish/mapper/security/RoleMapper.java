package com.saltedfish.mapper.security;

import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.annotations.Param;

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
	/**
	 * <p>添加角色表</p>
	 * @return
	 * @author lkd
	 */
	public void addRole(SysRoles role);
	/**
	 * <p>添加角色资源关联表</p>
	 * @return
	 * @author lkd
	 */
	public void addRoleResRelation(@Param("roleId")String roleId, @Param("resId")String resId);

	/**
	 * <p>查询角色信息</p>
	 * @param roleId
	 * @return
	 */
	public SysRoles queryRoleById(Integer roleId);

	/**
	 * @param role
	 */
	public void updateRole(SysRoles role);

	/**
	 * 根据角色Id 删除角色资源关联关系
	 * @param roleId
	 */
	public void deleteRoleResourceRelation(Integer roleId);

	/**
	 * @param roleId
	 */
	public void deleteRoelById(Integer roleId);

	/**
	 * @param roleId 
	 */
	public void updateUserEnabledByRoleId(Integer roleId);

	/**
	 * @param roleId
	 */
	public void deleteUserRoleRelation(Integer roleId); 
}
