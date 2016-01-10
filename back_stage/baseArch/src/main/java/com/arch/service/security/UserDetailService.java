package com.arch.service.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.arch.dto.UserDTO;
import com.arch.entity.security.SysRoles;
import com.arch.entity.security.SysUsers;
import com.arch.mapper.security.RoleMapper;
import com.arch.mapper.security.UserMapper;
import com.arch.utils.BeanCopierUtils;


/**
 * spring-security 核心登陆功能
 * @author lkd
 *
 */
public class UserDetailService implements UserDetailsService {

	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private RoleMapper  roleMapper;
	
	@Autowired
	private MessageSource messageSource;
	
	/**
	 * 根据userName 查询用户,并且加载该用户的所有角色
	 */
	public UserDetails loadUserByUsername(String userName){
		
     	SysUsers user = userMapper.selectSysUserByName(userName);
		
		//用户名或者密码错误
		if (user == null) throw new UsernameNotFoundException("AbstractAccessDecisionManager.accessDenied");
			
		UserDTO userDTO = new UserDTO();
			BeanCopierUtils.copyProperties(user, userDTO);
			userDTO.setLoginName(userName);
			
			logger.info("======================"+userName+"==========================");
			
			 List<SysRoles> roles = roleMapper.selectRolesByUserId(user.getUserId());
			
			
			Collection<GrantedAuthority> authorities = loadUserAuthorities(roles);
			 
			userDTO.getAuthorities().addAll(authorities);
			 
			return userDTO;
	}
	//加载角色
	private Collection<GrantedAuthority> loadUserAuthorities(List<SysRoles> roles) {
		final Set<GrantedAuthority> gas = new HashSet<GrantedAuthority>(roles.size());
		for (SysRoles role : roles) {
			gas.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName().trim()));
		}
		return gas;
	}
	
	
	

}
