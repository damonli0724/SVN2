/*******************************************************************************
 * Project   : baseArch
 * Class Name: com.saltedfish.controller.base.BaseController
 * Created By: mjy 
 * Created on: 2016年1月12日 下午4:51:22
 * Copyright © 2013-2014 YYQ All rights reserved.
 ******************************************************************************/
package com.saltedfish.controller.base;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import com.saltedfish.constants.Constants;
import com.saltedfish.dto.BaseResultDTO;
import com.saltedfish.exception.ServiceException;


/**
 * <P>父类controller</P>
 * @author lkd
 */
public class BaseController {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 
	 * <p>防止前台传入的字符串不能转换为Date 报404错误</p>
	 * @param binder
	 * @author lkd
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   // true:允许输入空值，false:不能为空值

	}
	
	
	protected <T> BaseResultDTO<T> checkErrors(BindingResult bindingResult) {
		BaseResultDTO<T> result = new BaseResultDTO<T>();
		logger.debug("BaseController.checkErrors");
		// 检查请求参数信息是否有误
		if (bindingResult.hasErrors()) {
			StringBuffer message = new StringBuffer();
			for (ObjectError error : bindingResult.getAllErrors()) {
				if (error.getDefaultMessage() != null)
					message.append(error.getDefaultMessage()).append(";");
			}
			message.deleteCharAt(message.length() - 1);
			result.setStatus(Constants.R_STATUS_FAILTURE);
			result.setMessage(message.toString());
			return result;
		} else {
			return null;
		}
	}
	
	
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public BaseResultDTO<Object> exceptionHandler(Exception e) {
		BaseResultDTO<Object> result = new BaseResultDTO<Object>();
		e.printStackTrace();
		logger.error(e.getMessage());
		
		//如果是业务级别的异常，则返回错误信息,否则统统返回系统维护中
		if (e instanceof ServiceException) {
			e.printStackTrace();
			result.setStatus(Constants.R_STATUS_FAILTURE);
			result.setMessage(e.getMessage());
			return result;
		} else {
			result.setStatus(Constants.R_STATUS_FAILTURE);
			result.setMessage("系统维护中, 请稍后再试");
			return result;
		}
	}
	
}