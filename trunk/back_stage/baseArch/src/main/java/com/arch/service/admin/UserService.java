package com.arch.service.admin;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.arch.cmd.admin.AdminAddCmd;
import com.arch.cmd.admin.AdminListQueryCmd;
import com.arch.entity.security.SysUsers;
import com.arch.mapper.security.UserMapper;


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

	
	/**
	 * 添加管理员
	 * @param cmd
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 3)
	public void addAdminUser(AdminAddCmd cmd) {
		SysUsers  sysUser  = new SysUsers();
		sysUser.setAccountNonExpired(true);
		sysUser.setAccountNonLocked(true);
		sysUser.setCredentialsNonExpired(true);
		sysUser.setEnabled(true);
		sysUser.setDeadline(new Date());
		sysUser.setDtCreate(new Date());
		sysUser.setLastLogin(new Date());
		sysUser.setName(cmd.getName());
		sysUser.setUsername(cmd.getUserName());
		sysUser.setPassword(cmd.getConfirmPassword());
		sysUser.setEmail(cmd.getEmail());
		sysUser.setMobile(cmd.getMobile());
		sysUser.setDescription(cmd.getDescription());
		sysUser.setSex(cmd.getSex());
		
		userMapper.addAdminUser(sysUser);
		
	}

}
