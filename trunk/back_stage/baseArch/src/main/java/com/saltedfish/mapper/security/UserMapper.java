package com.saltedfish.mapper.security;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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

	/**
	 * <p>修改用户状态</p>
	 * @param userId
	 * @param enabled
	 * @author lkd
	 */
	public void endOrStartEnable(@Param("userId") Integer userId, @Param("enabled") Boolean enabled);

	/**
	 * <p>根据用户Id删除用户</p>
	 * @param userId
	 * @author lkd
	 */
	public void deleteUserByUserId(Integer userId);

}
