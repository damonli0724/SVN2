package com.saltedfish.service.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saltedfish.cmd.admin.RoleAddCmd;
import com.saltedfish.entity.security.SysRoles;
import com.saltedfish.mapper.security.RoleMapper;


@Service
public class RoleService {

	@Autowired
	private RoleMapper roleMapper;

	/**
	 * <p>查询所有的角色</p>
	 * @return
	 * @author lkd
	 */
	public List<SysRoles> queryAllRoles() {
		return roleMapper.queryAllRoles();
	}

	/**
	 * <p>添加角色，并且加入资源角色关联表</p>
	 * @param cmd
	 * @author lkd
	 */
	public void addRole(RoleAddCmd cmd) {

		// 1.插入角色表
		SysRoles role = new SysRoles();
		role.setEnable(true);
		role.setRoleDesc(cmd.getRoleDesc());
		role.setRoleName(cmd.getRoleName());
		// roleMapper.addRole(role);
	}

}
