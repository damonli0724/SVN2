package com.arch.security;

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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.arch.dto.UserDTO;
import com.arch.entity.security.SysResources;
import com.arch.entity.security.SysUsers;
import com.arch.mapper.security.ResourcesMapper;
import com.arch.mapper.security.RoleMapper;
import com.arch.mapper.security.UserMapper;
import com.arch.utils.BeanCopierUtils;


/**
 * spring-security 核心登陆功能
 * @author lkd
 *
 */
@Service
public class MyUserDetailServiceImpl implements UserDetailsService {

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
			if (res==null)continue;
			authSet.add(new SimpleGrantedAuthority("ROLE"+res.getResKey()));
		}
		return authSet;
	}

}
