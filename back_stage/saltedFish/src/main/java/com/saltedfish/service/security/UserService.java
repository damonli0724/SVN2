package com.saltedfish.service.security;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.saltedfish.cmd.admin.AdminAddCmd;
import com.saltedfish.cmd.admin.AdminListQueryCmd;
import com.saltedfish.dto.security.UserListDTO;
import com.saltedfish.entity.security.SysUsers;
import com.saltedfish.mapper.security.UserMapper;
import com.saltedfish.utils.MD5Util;


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
	public List<UserListDTO> queryUsers(AdminListQueryCmd cmd) {
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
		SysUsers sysUser = new SysUsers();
		sysUser.setAccountNonExpired(true);
		sysUser.setAccountNonLocked(true);
		sysUser.setCredentialsNonExpired(true);
		sysUser.setEnabled(true);

		sysUser.setDtCreate(new Date());
		sysUser.setName(cmd.getName());
		sysUser.setUsername(cmd.getUserName());
		sysUser.setPassword(MD5Util.MD5(cmd.getConfirmPassword()));
		sysUser.setEmail(cmd.getEmail());
		sysUser.setMobile(cmd.getMobile());
		sysUser.setDescription(cmd.getDescription());
		sysUser.setSex(cmd.getSex());
		//添加用户
		userMapper.addAdminUser(sysUser);
		//添加用户拥有的角色
 		userMapper.addUserRoleRelation(sysUser.getUserId(),cmd.getRoleId());
	}

	/**
	 * <p>修改用户状态</p>
	 * @param userId
	 * @param enabled
	 * @author lkd
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 3)
	public void endOrStartEnable(Integer userId, Boolean enabled) {
		userMapper.endOrStartEnable(userId, enabled);
	}

	/**
	 * <p>根据用户Id删除用户</p>
	 * @param userId
	 * @author lkd
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 3)
	public void deleteUserByUserId(Integer userId) {
		userMapper.deleteUserByUserId(userId);
	}

	public UserListDTO queryUsersById(Integer userId) {
		return userMapper.queryUsersById(userId);
	}

	/**
	 * <p>修改用户数据</p>
	 * @param cmd
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 3)
	public void updateAdminUser(AdminAddCmd cmd) {
		SysUsers sysUser = new SysUsers();
		sysUser.setAccountNonExpired(true);
		sysUser.setAccountNonLocked(true);
		sysUser.setCredentialsNonExpired(true);
		sysUser.setEnabled(true);

		sysUser.setName(cmd.getName());
		sysUser.setUsername(cmd.getUserName());
		sysUser.setPassword(MD5Util.MD5(cmd.getConfirmPassword()));
		sysUser.setEmail(cmd.getEmail());
		sysUser.setMobile(cmd.getMobile());
		sysUser.setDescription(cmd.getDescription());
		sysUser.setSex(cmd.getSex());
		sysUser.setUserId(String.valueOf(cmd.getUserId()));
		//修改信息
		userMapper.updateAdminUser(sysUser);
		//修改用户拥有的角色
 		userMapper.updateUserRoleRelation(sysUser.getUserId(),cmd.getRoleId());
		
	}

}
