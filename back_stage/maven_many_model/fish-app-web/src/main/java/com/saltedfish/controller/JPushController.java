package com.saltedfish.controller;

import java.io.UnsupportedEncodingException;
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
			logger.info("===========encode=================>id"+id+"-title :"+title+"-content"+content);
			
		try {
			//Url解码 utf8
			title = java.net.URLDecoder.decode(title,"utf-8");
			content = java.net.URLDecoder.decode(content,"utf-8");
			
			logger.info("===========decode=================>id"+id+"-title :"+title+"-content"+content);
			
			JPushUtils.sendMessageToALL(title, content, id);
			result.setStatus(Constants.R_STATUS_SUCCESS);
			result.setMessage("send success!");
		} catch (Exception e) {
			result.setStatus(Constants.R_STATUS_FAILTURE);
			result.setMessage("error:"+e.getMessage());
		}
		return result;
	}

	
	public static void main(String[] args) throws Exception {
		String   mytext   =   java.net.URLEncoder.encode("这个是中文",   "utf-8");   
		System.err.println(mytext);
		String  mytext2 = java.net.URLDecoder.decode("content%E8%BF%99%E4%B8%AA%E6%98%AF%E4%B8%AD%E6%96%87", "utf-8");
		System.err.println(mytext2);
		
		
	}
	

}
