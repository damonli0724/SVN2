package com.saltedfish.controller.security;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.saltedfish.constants.Url;
import com.saltedfish.constants.View;
import com.saltedfish.entity.security.SysRoles;
import com.saltedfish.service.security.RoleService;
import com.saltedfish.service.security.UserService;


@Controller
public class RoleController {

	protected final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private RoleService roleService;

	/**
	 * 角色添加页面
	 * @return
	 */
	@RequestMapping(value = Url.ROLE_ADD_PAGE, method = RequestMethod.GET)
	public String turnToRoleAddPage() {
		return View.ROLE_ADD_VIEW;
	}

	
	/**
	 * 角色列表页面
	 * @return
	 */
	@RequestMapping(value = Url.ROLE_LIST_PAGE, method = RequestMethod.GET)
	public String turnToAdminAddPage(ModelMap map) {
		List<SysRoles> roles = roleService.queryAllRoles();
		map.put("roles", roles);
		map.put("count", roles.size());
		return View.ROLE_LIST_VIEW;
	}
	
	
	
	// /**
	// * 管理员添加
	// * @return
	// */
	// @RequestMapping(value = Url.ADMIN_ADD_DATA, method=RequestMethod.POST)
	// @ResponseBody
	// public BaseResultDTO<String> addAdmin(AdminAddCmd cmd){
	// BaseResultDTO<String> result = new BaseResultDTO<String>();
	// try {
	// if
	// (!(cmd.getOriginalPassword()!=null&&cmd.getConfirmPassword()!=null&&(cmd.getOriginalPassword().equals(cmd.getConfirmPassword()))))
	// {
	// result.setStatus(Constants.R_STATUS_FAILTURE);
	// result.setMessage("原始密码和初始密码不正确!");
	// return result;
	// }
	// userService.addAdminUser(cmd);
	// } catch (Exception e) {
	// logger.debug("============add Admin error :"+e.getMessage());
	// result.setStatus(Constants.R_STATUS_FAILTURE);
	// }
	// result.setStatus(Constants.R_STATUS_SUCCESS);
	// return result ;
	// }

}
