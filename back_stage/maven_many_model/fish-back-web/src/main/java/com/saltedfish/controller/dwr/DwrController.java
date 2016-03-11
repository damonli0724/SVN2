package com.saltedfish.controller.dwr;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cc.aicode.e2e.ExcelHelper;
import cc.aicode.e2e.exception.ExcelContentInvalidException;
import cc.aicode.e2e.exception.ExcelParseException;
import cc.aicode.e2e.exception.ExcelRegexpValidFailedException;

import com.alibaba.fastjson.JSON;
import com.saltedfish.controller.base.BaseController;
import com.saltedfish.controller.constants.Url;
import com.saltedfish.controller.constants.View;
import com.saltedfish.controller.excel.entity.CustomExcelHelper;
import com.saltedfish.controller.excel.entity.Demo;
import com.saltedfish.entity.security.SysUsers;


@Controller
public class DwrController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 跳转到dwr测试页面
	 * @return
	 */
	@RequestMapping(value = Url.DWR_SEND_PAGE, method = RequestMethod.GET)
	public String turnToWelcomPage(HttpServletRequest request) {
		//设置发送者的userId为1
		SysUsers  user  =  new SysUsers();
		user.setUserId("1");
		HttpSession  session = request.getSession();
		session.setAttribute("userId", 1);
		return View.DWR_SEND_VIEW;
	}

	/**
	 * 跳转到dwr测试页面
	 * @return
	 */
	@RequestMapping(value = Url.DWR_RECIVE_PAGE, method = RequestMethod.GET)
	public String turnSendPage(HttpServletRequest request) {
		//设置接收者的userId为2
		SysUsers  user  =  new SysUsers();
		user.setUserId("1");
		HttpSession  session = request.getSession();
		session.setAttribute("userId", 2);
		return View.DWR_REVICE_VIEW;
	}


}
