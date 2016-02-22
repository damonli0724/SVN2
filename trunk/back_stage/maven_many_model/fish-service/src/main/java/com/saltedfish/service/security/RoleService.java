package com.saltedfish.service.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.saltedfish.cmd.admin.RoleAddCmd;
import com.saltedfish.entity.security.SysRoles;
import com.saltedfish.mapper.security.RoleMapper;

@Service
public class RoleService {

	@Autowired
	private RoleMapper roleMapper;

	/**
	 * <p>
	 * 查询所有的角色
	 * </p>
	 * 
	 * @return
	 * @author lkd
	 */
	public List<SysRoles> queryAllRoles() {
		return roleMapper.queryAllRoles();
	}

	/**
	 * <p>
	 * 添加角色，并且加入资源角色关联表
	 * </p>
	 * 
	 * @param cmd
	 * @author lkd
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 3)
	public void addRole(RoleAddCmd cmd) {

		// 1.插入角色表
		SysRoles role = new SysRoles();
		role.setEnable(true);
		role.setRoleDesc(cmd.getRoleDesc());
		role.setRoleName(cmd.getRoleName());
		roleMapper.addRole(role);
		// 插入角色资源关联表
		String[] recources = cmd.getResources().split(",");
		for (String r : recources) {
			roleMapper.addRoleResRelation(role.getRoleId(), r);
		}
	}

	/**
	 * 查询角色信息
	 * 
	 * @param roleId
	 * @return
	 */
	public SysRoles queryRoleById(Integer roleId) {
		return roleMapper.queryRoleById(roleId);
	}

	/**
	 * 修改角色信息
	 * 
	 * @param cmd
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 3)
	public void updateRole(RoleAddCmd cmd) {
		// 1.修改角色表
		SysRoles role = new SysRoles();
		role.setEnable(true);
		role.setRoleDesc(cmd.getRoleDesc());
		role.setRoleName(cmd.getRoleName());
		role.setRoleId(String.valueOf(cmd.getRoleId()));
		roleMapper.updateRole(role);
		
		if (!(cmd.getResources()==null||"".equals(cmd.getResources()))) { //如果为空，则代表未修改权限，则不进行如下操作
			//根据roleId删除角色资源关联关系
			roleMapper.deleteRoleResourceRelation(cmd.getRoleId());
			// 插入角色资源关联表
			String[] recources = cmd.getResources().split(",");
			for (String r : recources) {
				roleMapper.addRoleResRelation(role.getRoleId(), r);
			}	
		}
		
	}

	/**
	 * 删除角色(删除角色-->删除该角色表数据，删除角色资源关联表数据，修改拥有该角色的用户 状态为失效（这种只针对于1对1关联关系）)
	 * @param roleId
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 3)
	public void deleteRole(Integer roleId) {
		//删除角色表
		roleMapper.deleteRoelById(roleId);
		//删除角色资源表数据 根据角色Id
		roleMapper.deleteRoleResourceRelation(roleId);
		//根据角色Id 修改该角色的用户为失效状态
		roleMapper.updateUserEnabledByRoleId(roleId);
		/*//删除所有用户角色表中的数据,根据角色Id
		roleMapper.deleteUserRoleRelation(roleId);*/
	}

}
