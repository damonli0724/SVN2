package com.saltedfish.controller.security;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.saltedfish.constants.Constants;
import com.saltedfish.constants.Url;
import com.saltedfish.constants.View;
import com.saltedfish.dto.BaseResultDTO;
import com.saltedfish.dto.security.ResourceJsonDTO;
import com.saltedfish.service.security.ResourceService;


@Controller
public class ResourceController {

	protected final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private ResourceService resourceService;

	/**
	 * 管理员添加页面
	 * @return
	 */
	@RequestMapping(value = Url.RESOURCE_ADD_PAGE, method = RequestMethod.GET)
	public String turnToAdminAddPage() {
		return View.ADMIN_ADD_VIEW;
	}

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
			logger.debug("============加载权限树数据异常 :" + e.getMessage());
			result.setStatus(Constants.R_STATUS_FAILTURE);
		}
		return result;
	}

}
