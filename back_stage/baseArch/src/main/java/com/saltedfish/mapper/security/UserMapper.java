package com.saltedfish.mapper.security;

import java.util.List;

import com.saltedfish.cmd.admin.AdminListQueryCmd;
import com.saltedfish.dto.security.UserListDTO;
import com.saltedfish.entity.security.SysUsers;


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
	public List<UserListDTO> queryUsers(AdminListQueryCmd cmd);

	public Integer queryUsersCount(AdminListQueryCmd cmd);

	public void addAdminUser(SysUsers sysUser);

}
