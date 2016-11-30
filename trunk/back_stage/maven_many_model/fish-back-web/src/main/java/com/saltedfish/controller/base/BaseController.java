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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.saltedfish.controller.fileUpload.UploadFileCtroller;


/**
 * <P>父类controller</P>
 * @author lkd
 */
public class BaseController {
	
	protected  final  Logger logger =LoggerFactory.getLogger(this.getClass());

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
}