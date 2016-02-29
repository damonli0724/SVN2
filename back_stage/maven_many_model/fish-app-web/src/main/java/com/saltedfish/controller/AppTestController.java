package com.saltedfish.controller;

import java.io.IOException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.commons.httpclient.HttpException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.saltedfish.cmd.ValidateCmd;
import com.saltedfish.constants.Constants;
import com.saltedfish.controller.base.BaseController;
import com.saltedfish.controller.constants.Url;
import com.saltedfish.dto.BaseResultDTO;
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
	 * 不需要权限的Url
	 * @param cmd
	 * @param startPage
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = Url.UNSECURED_TEST, method = RequestMethod.GET)
	@ResponseBody
	public BaseResultDTO<String> unsecuredTest() {
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
		redisUtil.set("sessionId", "accountId");
		return result;
	}

	/**
	 * 需要权限的Url
	 * @param cmd
	 * @param startPage
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = Url.SECURED_TEST, method = RequestMethod.GET)
	@ResponseBody
	public BaseResultDTO<String>  securedTest() {
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
		redisUtil.set("sessionId", "accountId");
		return result;
	}
	
	
	@RequestMapping(value=Url.EXCEPTION_VALIDATE_TEST,method=RequestMethod.GET)
	@ResponseBody
	public BaseResultDTO<String> exceptionAndValidateTest(@Valid ValidateCmd val ,BindingResult bindingResult){
		BaseResultDTO<String> result = null ;
		
		//返回校验信息
		BaseResultDTO<String> errorResult = checkErrors(bindingResult);
		if (errorResult != null) {
			return errorResult;
		}
		
		System.out.println(1/0);
		
		result = new BaseResultDTO<String>();
		result.setResult("fdsf");
		result.setStatus(Constants.R_STATUS_SUCCESS);
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
