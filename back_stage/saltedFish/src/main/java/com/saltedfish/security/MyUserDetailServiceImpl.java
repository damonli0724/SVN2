package com.saltedfish.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.saltedfish.dto.UserDTO;
import com.saltedfish.entity.security.SysResources;
import com.saltedfish.entity.security.SysUsers;
import com.saltedfish.mapper.security.ResourcesMapper;
import com.saltedfish.mapper.security.RoleMapper;
import com.saltedfish.mapper.security.UserMapper;
import com.saltedfish.utils.BeanCopierUtils;


/**
 * spring-security 核心登陆功能
 * @author lkd
 *
 */
@Service
public class MyUserDetailServiceImpl {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private ResourcesMapper resourcesMapper;

	public MyUserDetailServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 根据userName 查询用户,并且加载该用户的所有角色
	 */
	public UserDetails loadUserByUsername(String userName) {

		SysUsers user = userMapper.selectSysUserByName(userName);

		// 用户名或者密码错误
		if (user == null)
			throw new UsernameNotFoundException("AbstractAccessDecisionManager.accessDenied");

		UserDTO userDTO = new UserDTO();
		BeanCopierUtils.copyProperties(user, userDTO);
		userDTO.setLoginName(userName);

		logger.info("======================" + userName + "==========================");

		Collection<GrantedAuthority> authorities = loadUserAuthorities(user.getUserId());

		userDTO.getAuthorities().addAll(authorities);

		return userDTO;
	}

	// 加载角色
	private Collection<GrantedAuthority> loadUserAuthorities(String userId) {
		List<SysResources> resources = resourcesMapper.getResourcesByUserId(userId);
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		for (SysResources res : resources) {
			if (res == null)
				continue;
			authSet.add(new SimpleGrantedAuthority("ROLE_" + res.getResKey()));
		}
		return authSet;
	}

}
