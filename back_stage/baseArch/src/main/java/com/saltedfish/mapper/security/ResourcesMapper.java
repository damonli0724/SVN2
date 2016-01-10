package com.saltedfish.mapper.security;

import java.util.List;

import com.saltedfish.entity.security.SysResources;


public interface ResourcesMapper {

	/**
	 * <p>根据用户ID 查询用户的权限</p>
	 * @param userId
	 * @return
	 * @author lkd
	 */
	public List<SysResources> getResourcesByUserId(String userId);

}
