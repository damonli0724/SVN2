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
import com.arch.entity.SysResources;
import com.arch.entity.SysUsers;
import com.arch.mapper.securtity.ResourcesMapper;
import com.arch.mapper.securtity.RoleMapper;
import com.arch.mapper.securtity.UserMapper;
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
			// 用户可以访问的资源名称（或者说用户所拥有的权限） 注意：必须"ROLE_"开头,资源(主菜单，子菜单，按钮)
			// 关联代码：applicationContext-security.xml
			// 关联代码： MySecurityMetadataSource#loadResourceDefine
			authSet.add(new SimpleGrantedAuthority("ROLE_" + res.getResKey()));
		}
		return authSet;
	}

}
