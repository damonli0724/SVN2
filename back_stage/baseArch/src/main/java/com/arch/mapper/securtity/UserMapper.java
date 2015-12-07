package com.arch.mapper.securtity;

import com.arch.entity.SysUsers;



public interface UserMapper {

	/**
	 * <p>TODO</p>
	 * @return
	 * @author lkd
	 */
	public int getUsersCount();


	/**
	 * 根据登陆名  查询用户
	 * @param userName
	 * @return
	 */
	public SysUsers selectSysUserByName(String userName);



}
