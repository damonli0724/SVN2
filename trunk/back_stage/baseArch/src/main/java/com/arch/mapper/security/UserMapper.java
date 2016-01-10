package com.arch.mapper.security;

import java.util.List;

import com.arch.cmd.admin.AdminListQueryCmd;
import com.arch.entity.security.SysUsers;


public interface UserMapper {

	/**
	 * 根据登陆名  查询用户
	 * @param userName
	 * @return
	 */
	public SysUsers selectSysUserByName(String userName);

	/**
	 * 查询用户列表
	 * @param cmd
	 * @return SysUsers
	 */
	public List<SysUsers> queryUsers(AdminListQueryCmd cmd);

	public Integer queryUsersCount(AdminListQueryCmd cmd);

	public void addAdminUser(SysUsers sysUser);

}
