package com.arch.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arch.constants.Constants;
import com.arch.constants.Url;
import com.arch.constants.View;
import com.arch.dto.BaseResultDTO;
import com.arch.service.other.UserService;

@Controller
public class AdminManagerController {

	protected final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private UserService userService;
	/**
	 * 管理员列表页面
	 * @return
	 */
	@RequestMapping(value = Url.ADMIN_LIST_PAGE, method=RequestMethod.GET)
	public String turnToWelcomPage(){
		return View.ADMIN_LIST_VIEW;
	}
	
	/**
	 * 管理员列表数据加载
	 * @return
	 */
	@RequestMapping(value = Url.ADMIN_LIST_DATA, method=RequestMethod.GET)
	@ResponseBody
	public 	BaseResultDTO<Object> adminDataLoad(){
		BaseResultDTO<Object> result = new BaseResultDTO<Object>();
		List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < 10; i++) {
			Map map  = new HashMap();
			map.put("name", "lkd"+i);
			map.put("age", 19);
			map.put("high", 180);
			map.put("tel", "123"+1);
			data.add(map);
		}
		result.setResult(data);
		result.setStatus(Constants.R_STATUS_SUCCESS);
		result.setMessage("query success!");
		return result; 
	}

}
