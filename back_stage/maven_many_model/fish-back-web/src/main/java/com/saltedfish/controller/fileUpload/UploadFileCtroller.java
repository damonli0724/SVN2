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

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.saltedfish.constants.Constants;
import com.saltedfish.controller.base.BaseController;
import com.saltedfish.controller.constants.Url;
import com.saltedfish.controller.constants.View;
import com.saltedfish.dto.BaseResultDTO;
import com.saltedfish.service.UploadTestService;

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
	public void uploadPic( MultipartFile file, ModelMap model, HttpServletResponse response) {
		// 图片非空验证
		if (file == null || file.getSize() == 0) {
			model.addAttribute("message", "请上传图片");
			model.addAttribute("status", 0);
			printOut(model, response);
			return;
		}
		String fileName = file.getOriginalFilename().toLowerCase();
		if (!fileName.endsWith("jpg") && !fileName.endsWith("png") && !fileName.endsWith("jpeg") && !fileName.endsWith("gif")) {
			model.put("status", 0);
			model.put("message", "上传文件不是jpg,png,jpeg,gif文件");
			printOut(model, response);
			return;
		}
		String picPath = "";
		
		try {
			picPath = service.uploadFileProcess(file);
			model.addAttribute("message", "上传图片成功");
			model.addAttribute("status", 1);
			model.addAttribute("picPath", picPath);
		} catch (IOException e) {
			model.addAttribute("message", "上传图片失败，请稍后再试！");
			model.addAttribute("status", 0);
		}
		printOut(model, response);
	}

	@SuppressWarnings("all")
	private void printOut(Map modelMap, HttpServletResponse response) {
		PrintWriter out;
		try {
			response.setContentType("text/html;charset=UTF-8");
			out = response.getWriter();
			out.print(JSON.toJSONString(modelMap));
		} catch (IOException e) {
			e.printStackTrace();
			// logger.error("输出到客户端出现异常：{}", modelMap);
			modelMap.put("message", e.getCause().getMessage());
			return;
		}
	}
	
}
