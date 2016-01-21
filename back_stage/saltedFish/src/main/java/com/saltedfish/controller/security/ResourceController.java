package com.saltedfish.controller.security;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.saltedfish.cmd.admin.ResListQueryCmd;
import com.saltedfish.cmd.admin.RoleAddCmd;
import com.saltedfish.constants.Constants;
import com.saltedfish.constants.Url;
import com.saltedfish.constants.View;
import com.saltedfish.dto.BaseResultDTO;
import com.saltedfish.dto.security.ResourceJsonDTO;
import com.saltedfish.entity.security.SysResources;
import com.saltedfish.service.security.ResourceService;
import com.saltedfish.service.security.RoleService;


@Controller
public class ResourceController {

	protected final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private ResourceService resourceService;

	@Autowired
	private RoleService roleService;

	/**
	* 根据角色加载权限树
	* @return
	*/
	@RequestMapping(value = Url.RESOURCE_TREE_DATA, method = RequestMethod.GET)
	@ResponseBody
	public BaseResultDTO<List<ResourceJsonDTO>> loadTreeData(Integer roleId) {
		BaseResultDTO<List<ResourceJsonDTO>> result = new BaseResultDTO<List<ResourceJsonDTO>>();
		try {

			List<ResourceJsonDTO> allResource = resourceService.loadTreeDataAll(); // 查询所有的资源权限

			if (roleId == null) { // 如果不传入角色Id 则默认查询所有
				result.setResult(allResource);
				result.setStatus(Constants.R_STATUS_SUCCESS);
				return result;
			}

			List<ResourceJsonDTO> roleResource = resourceService.loadTreeDataByRoleId(roleId);  // 根据角色Id

			for (ResourceJsonDTO r : allResource) {
				for (ResourceJsonDTO rr : roleResource) {

					if (rr.getId() != null && r.getId() != null && (rr.getId() == r.getId())) {  // 设置为选中
						r.setChecked(true);
					}
					if (rr.getType() != null && rr.getType() == Constants.RESOURCE_MENU) { // 如果是主菜单，则展开
						r.setOpen(true);
					}
				}

			}
			result.setResult(allResource);
			result.setStatus(Constants.R_STATUS_SUCCESS);
		} catch (Exception e) {
			logger.debug("-------->加载权限树数据异常 :" + e.getMessage());
			result.setStatus(Constants.R_STATUS_FAILTURE);
		}
		return result;
	}

	
	// ***********************************************************************************************
	/**
	 * 权限添加页面
	 * @return
	 */
	@RequestMapping(value = Url.RESOURCE_ADD_PAGE, method = RequestMethod.GET)
	public String turnToResAddPage() {
		return View.ADMIN_ADD_VIEW;
	}

	/**
	 * 权限列表页面
	 * @return
	 */
	@RequestMapping(value = Url.RESOURCE_LIST_PAGE, method = RequestMethod.GET)
	public String turnToResListPage() {
		return View.RESOURCE_LIST_VIEW;
	}

	/**
	 * 资源列表数据加载
	 * @return
	 */
	@RequestMapping(value = Url.RESOURCE_LIST_DATA, method = RequestMethod.GET)
	@ResponseBody
	public BaseResultDTO<List<SysResources>> resourceDataLoad(ResListQueryCmd cmd) {
		logger.debug("-------->resourceDataLoad 参数为:" + cmd.toString());

		BaseResultDTO<List<SysResources>> result = new BaseResultDTO<List<SysResources>>();
		try {

			List<SysResources> res = resourceService.queryResources(cmd);

			Integer count = resourceService.queryResourcesCount(cmd);
			result.setResult(res);
			result.setCount(count);
			result.setStatus(Constants.R_STATUS_SUCCESS);
			result.setMessage("query success!");
		} catch (Exception e) {
			logger.error("-------->resourceDataLoad 异常:" + e.getMessage());
			result.setStatus(Constants.R_STATUS_FAILTURE);
		}
		return result;
	}

}
