package com.saltedfish.controller.security;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.saltedfish.cmd.admin.ResAddCmd;
import com.saltedfish.cmd.admin.ResListQueryCmd;
import com.saltedfish.constants.Constants;
import com.saltedfish.constants.Url;
import com.saltedfish.constants.View;
import com.saltedfish.dto.BaseResultDTO;
import com.saltedfish.dto.security.ResourceJsonDTO;
import com.saltedfish.dto.security.ResourceListDTO;
import com.saltedfish.dto.security.ResourceQueryByParentIdDTO;
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
	* 根据角色加载资源树
	* @return
	*/
	@RequestMapping(value = Url.RESOURCE_TREE_DATA, method = RequestMethod.GET)
	@ResponseBody
	public BaseResultDTO<List<ResourceJsonDTO>> loadTreeData(Integer roleId) {
		BaseResultDTO<List<ResourceJsonDTO>> result = new BaseResultDTO<List<ResourceJsonDTO>>();
		try {

			List<ResourceJsonDTO> allResource = resourceService.loadTreeDataAll(); // 查询所有的资源资源

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
			logger.debug("-------->加载资源树数据异常 :" + e.getMessage());
			result.setStatus(Constants.R_STATUS_FAILTURE);
		}
		return result;
	}

	/**
	 * 资源添加页面
	 * @return
	 */
	@RequestMapping(value = Url.RESOURCE_ADD_PAGE, method = RequestMethod.GET)
	public String turnToResAddPage() {
		return View.RESOURCE_ADD_VIEW;
	}

	/**
	 * 资源添加
	 * @return
	 */
	@RequestMapping(value = Url.RESOURCE_ADD_DATA, method = RequestMethod.GET)
	@ResponseBody
	public BaseResultDTO<String> resourceDataAdd(ResAddCmd cmd) {
		logger.debug("-------->resourceDataAdd 参数为:" + cmd.toString());

		BaseResultDTO<String> result = new BaseResultDTO<String>();
		try {
			resourceService.addResource(cmd);
			result.setStatus(Constants.R_STATUS_SUCCESS);
		} catch (Exception e) {
			logger.error("-------->resourceDataAdd 异常:" + e.getMessage());
			result.setStatus(Constants.R_STATUS_FAILTURE);
		}
		return result;
	}

	/**
	 * 资源列表页面
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
	public BaseResultDTO<List<ResourceListDTO>> resourceDataLoad(ResListQueryCmd cmd) {
		logger.debug("-------->resourceDataLoad 参数为:" + cmd.toString());

		BaseResultDTO<List<ResourceListDTO>> result = new BaseResultDTO<List<ResourceListDTO>>();
		try {
			List<ResourceListDTO> res = resourceService.queryResources(cmd);

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

	/**
	 * 
	 * <p>根据父Id 加载菜单，子菜单，按钮资源</p>
	 * @param parentId
	 * @return
	 * @author LKD
	 */
	@RequestMapping(value = Url.RESOURCE_LOAD_BY_PARENTID, method = RequestMethod.GET)
	@ResponseBody
	public BaseResultDTO<List<ResourceQueryByParentIdDTO>> loadResByParentId(Integer parentId) {
		logger.debug("-------->loadResByParentId 参数为:" + parentId.toString());
		BaseResultDTO<List<ResourceQueryByParentIdDTO>> result = new BaseResultDTO<List<ResourceQueryByParentIdDTO>>();
		try {
			List<ResourceQueryByParentIdDTO> res = resourceService.queryResourcesByParent(parentId);

			result.setResult(res);
			result.setCount(res.size());
			result.setStatus(Constants.R_STATUS_SUCCESS);
		} catch (Exception e) {
			logger.error("-------->loadResByParentId 异常:" + e.getMessage());
			result.setStatus(Constants.R_STATUS_FAILTURE);
		}
		return result;
	}

}
