package com.saltedfish.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.saltedfish.cmd.admin.AdminListQueryCmd;
import com.saltedfish.constants.Constants;
import com.saltedfish.controller.base.BaseController;
import com.saltedfish.controller.constants.Url;
import com.saltedfish.dto.BaseResultDTO;
import com.saltedfish.dto.security.UserListDTO;
import com.saltedfish.service.security.RoleService;
import com.saltedfish.service.security.UserService;
import com.saltedfish.utils.JPushUtils;


@Controller
public class JPushController extends BaseController {

	protected final Logger logger = Logger.getLogger(this.getClass());


	/**
	 * 管理员列表数据加载
	 * @return
	 */
	@RequestMapping(value = Url.JPUSH_PUSH_MESSAGE, method = RequestMethod.POST)
	@ResponseBody
	public BaseResultDTO<String> adminDataLoad(String id,String title,String content) {
		BaseResultDTO<String> result = new BaseResultDTO<String>();

		try {
			JPushUtils.sendMessageToALL(title, content, id);
			result.setStatus(Constants.R_STATUS_SUCCESS);
			result.setMessage("send success!");
		} catch (Exception e) {
			result.setStatus(Constants.R_STATUS_FAILTURE);
			result.setMessage("error:"+e.getMessage());
		}
		return result;
	}

	

}
