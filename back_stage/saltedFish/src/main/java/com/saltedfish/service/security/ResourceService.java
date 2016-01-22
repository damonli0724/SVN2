package com.saltedfish.service.security;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.saltedfish.cmd.admin.ResAddCmd;
import com.saltedfish.cmd.admin.ResListQueryCmd;
import com.saltedfish.dto.security.ResourceJsonDTO;
import com.saltedfish.dto.security.ResourceListDTO;
import com.saltedfish.dto.security.ResourceQueryByParentIdDTO;
import com.saltedfish.entity.security.SysResources;
import com.saltedfish.mapper.security.ResourcesMapper;


/**
 * 资源Service
 * @author lkd
 *
 */
@Service
public class ResourceService {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private ResourcesMapper resourcesMapper;

	/**
	 * <p>根据角色Id查询 用户所有的权限</p>
	 * @param roleId
	 * @return
	 * @author lkd
	 */
	public List<ResourceJsonDTO> loadTreeDataByRoleId(Integer roleId) {
		return resourcesMapper.loadTreeDataByRoleId(roleId);
	}

	/**
	 * <p>查询所有的 权限</p>
	 * @return
	 * @author lkd
	 */
	public List<ResourceJsonDTO> loadTreeDataAll() {
		return resourcesMapper.loadTreeDataAll();
	}

	/**
	 * <p>查询资源列表数据</p>
	 * @param resName
	 * @param startPage
	 * @param pageSize
	 * @return
	 * @author lkd
	 */
	public List<ResourceListDTO> queryResources(ResListQueryCmd cmd) {
		return resourcesMapper.queryResources(cmd);
	}

	/**
	 * <p>资源列表总数</p>
	 * @param resName
	 * @return
	 * @author LKD
	 */
	public Integer queryResourcesCount(ResListQueryCmd cmd) {
		return resourcesMapper.queryResourcesCount(cmd);
	}

	/**
	 * <p>资源添加</p>
	 * @param cmd
	 * @author LKD
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 3)
	public void addResource(ResAddCmd cmd) {
		SysResources res = new SysResources();
		res.setDescription(cmd.getResDes());
		res.setLevel(cmd.getResLevel());
		res.setName(cmd.getResName());
		res.setParentId(cmd.getResParentId());
		res.setResKey(cmd.getResKey());
		res.setResUrl(cmd.getResUrl());
		res.setType(String.valueOf(cmd.getResType()));
		resourcesMapper.addResource(res);
	}

	/**
	 * <p>根据父Id 加载菜单，子菜单，按钮资源</p>
	 * @param parentId
	 * @return
	 * @author LKD
	 */
	public List<ResourceQueryByParentIdDTO> queryResourcesByParent(Integer parentId) {
		return resourcesMapper.queryResourcesByParent(parentId);
	}

}
