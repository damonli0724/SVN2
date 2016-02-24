package com.saltedfish.security.custom;

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

import com.saltedfish.dto.UserDTO;
import com.saltedfish.entity.security.SysResources;
import com.saltedfish.entity.security.SysUsers;
import com.saltedfish.mapper.security.ResourcesMapper;
import com.saltedfish.mapper.security.RoleMapper;
import com.saltedfish.mapper.security.UserMapper;
import com.saltedfish.utils.BeanCopierUtils;

public class CustomUserDetailsServiceImpl implements UserDetailsService{

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private ResourcesMapper resourcesMapper;


	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		SysUsers user = userMapper.selectSysUserByName(username);

		// 用户名或者密码错误
		if (user == null)
			throw new UsernameNotFoundException("该用户不存在！");

		UserDTO userDTO = new UserDTO();
		BeanCopierUtils.copyProperties(user, userDTO);
		userDTO.setLoginName(username);

		logger.info("======================" + username + "==========================");

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
