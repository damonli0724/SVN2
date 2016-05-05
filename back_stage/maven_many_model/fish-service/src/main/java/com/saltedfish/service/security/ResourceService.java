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
import com.saltedfish.dto.security.ResourceUpdateDTO;
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

	public List<ResourceJsonDTO> loadTreeDataByRoleId(Integer roleId) {
		return resourcesMapper.loadTreeDataByRoleId(roleId);
	}
	public List<ResourceJsonDTO> loadTreeDataAll() {
		return resourcesMapper.loadTreeDataAll();
	}

	 
	public List<ResourceListDTO> queryResources(ResListQueryCmd cmd) {
		return resourcesMapper.queryResources(cmd);
	}

	public Integer queryResourcesCount(ResListQueryCmd cmd) {
		return resourcesMapper.queryResourcesCount(cmd);
	}

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

	public List<ResourceQueryByParentIdDTO> queryResourcesByParent(Integer parentId) {
		return resourcesMapper.queryResourcesByParent(parentId);
	}

	
	 
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 3)
	public void deleteResourceByResId(Integer resId) {
		resourcesMapper.deleteResourceByResId(resId); //删除资源表数据
		resourcesMapper.deleteRoleResourceRelation(resId); //删除角色资源关联关系
	}
	public ResourceUpdateDTO queryResourceForUpdate(Integer resId) {
		return resourcesMapper.queryResourceForUpdate(resId);
	}
	 
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 3)
	public void updateResource(ResAddCmd cmd) {
		SysResources res = new SysResources();
		res.setDescription(cmd.getResDes());
		res.setLevel(cmd.getResLevel());
		res.setName(cmd.getResName());
		res.setParentId(cmd.getResParentId());
		res.setResKey(cmd.getResKey());
		res.setResUrl(cmd.getResUrl());
		res.setType(String.valueOf(cmd.getResType()));
		res.setId(cmd.getResId());
		
		resourcesMapper.updateResource(res);
	}

	

}
