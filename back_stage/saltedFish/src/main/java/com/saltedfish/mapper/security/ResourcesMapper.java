package com.saltedfish.mapper.security;

import java.util.List;

import com.saltedfish.cmd.admin.ResAddCmd;
import com.saltedfish.cmd.admin.ResListQueryCmd;
import com.saltedfish.dto.security.ResourceJsonDTO;
import com.saltedfish.dto.security.ResourceListDTO;
import com.saltedfish.dto.security.ResourceQueryByParentIdDTO;
import com.saltedfish.dto.security.ResourceUpdateDTO;
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
	public List<ResourceListDTO> queryResources(ResListQueryCmd cmd);

	/**
	 * <p>查询资源总数</p>
	 * @param resName
	 * @return
	 * @author LKD
	 */
	public Integer queryResourcesCount(ResListQueryCmd cmd);

	/**
	 * <p>添加资源</p>
	 * @param res
	 * @author LKD
	 */
	public void addResource(SysResources res);

	/**
	 * <p>根据父Id 加载菜单，子菜单，按钮资源</p>
	 * @param parentId
	 * @return
	 * @author LKD
	 */
	public List<ResourceQueryByParentIdDTO> queryResourcesByParent(Integer parentId);

	/**
	 *  删除资源表
	 * @param resId
	 */
	public void deleteResourceByResId(Integer resId);

	/**
	 * 	删除角色资源关联关系
	 * @param resId
	 */
	public void deleteRoleResourceRelation(Integer resId);

	/**
	 *  修改页面返回的DTO
	 * @param resId
	 * @return
	 */
	public ResourceUpdateDTO queryResourceForUpdate(Integer resId);

	/**
	 * 修改资源
	 */
	public void updateResource(SysResources res);


}
