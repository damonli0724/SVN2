package com.saltedfish.mapper.security;

import java.util.List;

import com.saltedfish.dto.security.ResourceJsonDTO;
import com.saltedfish.entity.security.SysResources;


public interface ResourcesMapper {

	/**
	 * <p>根据用户ID 查询用户的权限</p>
	 * @param userId
	 * @return
	 * @author lkd
	 */
	public List<SysResources> getResourcesByUserId(String userId);

	/**
	 * <p>根据角色Id查询 用户所有的权限</p>
	 * @param roleId
	 * @return
	 * @author lkd
	 */
	public List<ResourceJsonDTO> loadTreeDataByRoleId(Integer roleId);

	/**
	 * <p>查询所有的 权限</p>
	 * @return
	 * @author lkd
	 */
	public List<ResourceJsonDTO> loadTreeDataAll();

}
