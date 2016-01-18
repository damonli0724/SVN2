package com.saltedfish.service.security;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saltedfish.dto.security.ResourceJsonDTO;
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
		// TODO Auto-generated method stub
		return resourcesMapper.loadTreeDataAll();
	}

}
