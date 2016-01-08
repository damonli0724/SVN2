package com.arch.service.other;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arch.cmd.admin.AdminListQueryCmd;
import com.arch.entity.SysUsers;
import com.arch.mapper.securtity.UserMapper;


@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	/**
	 * <p>查询用户总数</p>
	 * @return
	 * @author lkd
	 * 
	 */
	public List<SysUsers> queryUsers(AdminListQueryCmd cmd) {
		return userMapper.queryUsers(cmd);
	}

	public Integer queryUsersCount(AdminListQueryCmd cmd) {
		return userMapper.queryUsersCount(cmd);
	}

	/**
	 * <p>根据登录名查询用户</p>
	 * @param username
	 * @return
	 * @author lkd
	 */
	public SysUsers selectSysUserByName(String username) {
		return userMapper.selectSysUserByName(username);
	}

}
