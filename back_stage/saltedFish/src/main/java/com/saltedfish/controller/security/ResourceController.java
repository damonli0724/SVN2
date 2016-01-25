package com.saltedfish.controller.security;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
import com.saltedfish.dto.security.ResourceUpdateDTO;
import com.saltedfish.service.security.ResourceService;
import com.saltedfish.service.security.RoleService;


@Controller
public class ResourceController {

	protected final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private ResourceService resourceService;

	@Autowired
	private RoleService roleService;

	// =================================================增===========================================================
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
	@RequestMapping(value = Url.RESOURCE_ADD_DATA, method = RequestMethod.POST)
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

	// =================================================删===========================================================
	/**
	 * 资源删除
	 * @return
	 */
	@RequestMapping(value = Url.RESOURCE_DELETE, method = RequestMethod.POST)
	@ResponseBody
	public BaseResultDTO<String> resourceDatadelete(Integer resId) {
		logger.debug("-------->resourceDatadelete 参数为:" + resId.toString());

		BaseResultDTO<String> result = new BaseResultDTO<String>();
		try {
			resourceService.deleteResourceByResId(resId);
			result.setStatus(Constants.R_STATUS_SUCCESS);
		} catch (Exception e) {
			logger.error("-------->resourceDatadelete 异常:" + e.getMessage());
			result.setStatus(Constants.R_STATUS_FAILTURE);
		}
		return result;
	}

	// =================================================改===========================================================
	/**
	 * 资源修改页面
	 * @return
	 */
	@RequestMapping(value = Url.RESOURCE_UPDATE_PAGE, method = RequestMethod.GET)
	public String turnToResUpdatePage(Integer resId, ModelMap map) {
		ResourceUpdateDTO dto = resourceService.queryResourceForUpdate(resId);
		map.addAttribute("res", dto);
		return View.RESOURCE_UPDATE_VIEW;
	}

	/**
	 * 资源修改
	 * @return
	 */
	@RequestMapping(value = Url.RESOURCE_UPDATE_DATA, method = RequestMethod.POST)
	@ResponseBody
	public BaseResultDTO<String> resourceDataUpdate(ResAddCmd cmd) {
		logger.debug("-------->resourceDataUpdate 参数为:" + cmd.toString());

		BaseResultDTO<String> result = new BaseResultDTO<String>();
		try {
			resourceService.updateResource(cmd);
			result.setStatus(Constants.R_STATUS_SUCCESS);
		} catch (Exception e) {
			logger.error("-------->resourceDataUpdate 异常:" + e.getMessage());
			result.setStatus(Constants.R_STATUS_FAILTURE);
		}
		return result;
	}

	// =================================================查===========================================================
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
				if (r.getId().equals(Constants.RESOURCE_TREE_ROOT_ID)) {
					r.setChecked(true);
					continue;
				}
				for (ResourceJsonDTO rr : roleResource) {
					if ((rr.getId() != null && r.getId() != null && (rr.getId().equals(r.getId())))) {  // 设置为选中
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
	@RequestMapping(value = Url.RESOURCE_LIST_DATA, method = RequestMethod.POST)
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

	// =================================================END===========================================================

}
