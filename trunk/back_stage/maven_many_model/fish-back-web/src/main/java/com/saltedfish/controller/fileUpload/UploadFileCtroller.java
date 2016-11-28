/**   
 * Copyright © 2016 公司名. All rights reserved.
 * 
 * @Title: uploadFile.java 
 * @Prject: fish-back-web
 * @Package: com.saltedfish.controller 
 * @Description: TODO
 * @author: mjy   
 * @date: 2016年11月28日 下午5:08:54 
 * @version: V1.0   
 */
package com.saltedfish.controller.fileUpload;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.saltedfish.controller.constants.Url;
import com.saltedfish.controller.constants.View;

/** 
 * @ClassName: uploadFile 
 * @Description: 文件上传
 * @author: lkd
 * @date: 2016年11月28日 下午5:08:54  
 */
@Controller
public class UploadFileCtroller {

	/**
	 * 
	 * @Title: turnToUploadPage 
	 * @Description: 跳转到文件上传页面
	 * @param request
	 * @param map
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = Url.FILE_UPLOAD_TEST_PAGE, method = RequestMethod.GET)
	public String turnToUploadPage(HttpServletRequest request,ModelMap map) {
		
		return View.UPLOAD_TEST_VIEW;
	}
	
	
	
}
