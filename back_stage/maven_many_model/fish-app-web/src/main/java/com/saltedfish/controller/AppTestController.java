package com.saltedfish.controller;

import java.io.IOException;

import org.apache.commons.httpclient.HttpException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.saltedfish.constants.Constants;
import com.saltedfish.controller.base.BaseController;
import com.saltedfish.controller.constants.Url;
import com.saltedfish.dto.BaseResultDTO;
import com.saltedfish.dto.UserDTO;
import com.saltedfish.entity.security.SysUsers;
import com.saltedfish.exception.SMSException;
import com.saltedfish.sms.SmsSendService;
import com.saltedfish.utils.RedisUtil;


@Controller
public class AppTestController extends BaseController {

	protected final Logger logger = Logger.getLogger(this.getClass());

	
	@Autowired
	private SmsSendService smsSendService;
	@Autowired
	private RedisUtil redisUtil;


	/**
	 * redis 测试
	 * @param cmd
	 * @param startPage
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = Url.ADMIN_LIST_DATA, method = RequestMethod.GET)
	@ResponseBody
	public BaseResultDTO<String> adminDataLoad() {
		BaseResultDTO<String> result = new BaseResultDTO<String>();
		result.setStatus(Constants.R_STATUS_SUCCESS);
		System.err.println(redisUtil.exists("lkd")+"==============================");
		SysUsers user = new SysUsers();
		user.setDepName("=======================================");
		redisUtil.set("lkd", user);
		System.err.println(redisUtil.exists("lkd")+"==============================");
		user = (SysUsers)redisUtil.get("lkd");
		System.err.println(user.toString()+"===========================");
		result.setMessage("query success!");
		return result;
	}

	/**
	 * 手机短信发送
	 * @return
	 */
	@RequestMapping(value = Url.SMS_SEND_MESSAGE, method = RequestMethod.POST)
	@ResponseBody
	public BaseResultDTO<String> smsMessageSend(String tel,String content) {
		logger.info("smsMessage send:tel"+tel+" content:"+content);
		BaseResultDTO<String> result = new BaseResultDTO<String>();
		try {
			smsSendService.sendMessage(tel, content);
			result.setStatus(Constants.R_STATUS_SUCCESS);
			result.setMessage("send success!");
		} catch (HttpException e) {
			result.setStatus(Constants.R_STATUS_FAILTURE);
			result.setMessage("send fail："+e.getMessage());
		} catch (IOException e) {
			result.setStatus(Constants.R_STATUS_FAILTURE);
			result.setMessage("send fail："+e.getMessage());
		} catch (SMSException e) {
			result.setStatus(Constants.R_STATUS_FAILTURE);
			result.setMessage("send fail："+e.getMessage());
		}
		
		return result;
	}
	
	

}
