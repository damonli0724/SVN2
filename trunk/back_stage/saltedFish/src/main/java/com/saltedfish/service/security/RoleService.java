package com.saltedfish.service.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
