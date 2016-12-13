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

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.saltedfish.constants.Constants;
import com.saltedfish.constants.ExceptionCode;
import com.saltedfish.controller.base.BaseController;
import com.saltedfish.controller.constants.Url;
import com.saltedfish.controller.constants.View;
import com.saltedfish.dto.BaseResultDTO;
import com.saltedfish.exception.SystemException;
import com.saltedfish.service.UploadTestService;
import com.saltedfish.utils.FileUtil;

/** 
 * @ClassName: uploadFile 
 * @Description: 文件上传
 * @author: lkd
 * @date: 2016年11月28日 下午5:08:54  
 */
@Controller
public class UploadFileCtroller extends BaseController{
	
	@Autowired
	private UploadTestService service;

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
	
	
	
	@RequestMapping(value = Url.FILE_UPLOAD_TEST, method = RequestMethod.POST)
	@ResponseBody
	public BaseResultDTO<String> uploadFile(MultipartFile  file ,HttpServletRequest request){
		
		BaseResultDTO<String>  res = new BaseResultDTO<String>();
		
		
		//ajaxfileUpload 携带的参数
		String id = request.getParameter("id");
		String name = request.getParameter("name");
	
		logger.debug("使用ajaxfileUpload 前端传递的参数为id[{}],name[{}],file[{}]",new String[]{id,name,file.toString()});
		
		
		
		try {
			
			
			service.uploadFileProcess(file);
			res.setMessage("上传图片成功!");
			res.setResult(Constants.R_STATUS_SUCCESS);
		} catch (Exception e) {
			res.setMessage(e.getMessage());
			res.setResult(Constants.R_STATUS_FAILTURE);
			e.printStackTrace();
		}
		return res;
	}
	
	
	
}
