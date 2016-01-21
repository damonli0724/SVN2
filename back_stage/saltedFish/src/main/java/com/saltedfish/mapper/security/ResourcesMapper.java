package com.saltedfish.mapper.security;

import java.util.List;

import com.saltedfish.cmd.admin.ResListQueryCmd;
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

	/**
	 * <p>查询权限列表数据</p>
	 * @param resName
	 * @param startPage
	 * @param pageSize
	 * @return
	 * @author lkd
	 */
	public List<SysResources> queryResources(ResListQueryCmd cmd);

	/**
	 * <p>查询资源总数</p>
	 * @param resName
	 * @return
	 * @author LKD
	 */
	public Integer queryResourcesCount(ResListQueryCmd cmd);

}
