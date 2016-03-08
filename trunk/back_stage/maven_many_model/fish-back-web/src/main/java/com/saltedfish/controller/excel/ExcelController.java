package com.saltedfish.controller.excel;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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


@Controller
public class ExcelController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 管理员列表页面
	 * @return
	 */
	@RequestMapping(value = Url.EXCEL_ADD_PAGE, method = RequestMethod.GET)
	public String turnToWelcomPage() {
		return View.EXCEL_ADD_VIEW;
	}

	/**
	 * 管理员列表数据加载
	 * @return
	 */
	@RequestMapping(value = Url.EXCEL_ADD_DATA, method = RequestMethod.POST)
	@ResponseBody
	public synchronized void excelImport(@RequestParam("excelFile") MultipartFile file, HttpServletResponse response) {

		Map<String, Object> modelMap = new HashMap();
		// modelMap.put("successFlag", false);
		String fileName = file.getOriginalFilename();
		if (StringUtils.isEmpty(file)) {
			modelMap.put("message", "没有待上传的文件");
			printOut(modelMap, response);
			return;
		}
		if (!fileName.endsWith("xls") && !fileName.endsWith("xlsx")) {
			modelMap.put("message", "上传文件不是EXCEL文件");
			printOut(modelMap, response);
			return;
		}
		 ExcelHelper eh = null;
		try {
			//读excel文件,生成二维数组
			eh = ExcelHelper.readExcel(file.getInputStream());
		} catch (InvalidFormatException | IOException | NullPointerException e1) {
			e1.printStackTrace();
			logger.error("读取EXCEL文件出现异常{}", e1);
			modelMap.put("message", "读取EXCEL文件出现异常");
			printOut(modelMap, response);
			return;
		}
			//头
		 	String[] headers = eh.getHeaders();
		 	//数据
		 	String[][] datas = eh.getDatas();

	        
	        
	        List<Demo> entitys = null;
	        try {
	        	//根据Demo.class进行校验
	            entitys = eh.toEntitys(Demo.class);
	            for (Demo d : entitys) {
	                System.out.println("==================>"+d.toString());
	            }
	        } catch (ExcelParseException | ExcelContentInvalidException | ExcelRegexpValidFailedException e) {
				e.printStackTrace();
				if (e.getCause() != null) {
					logger.error("解析EXCEL出现异常{}", e.getCause().getMessage());
					modelMap.put("message", "EXCEL解析格式错误：[" + e.getCause().getMessage() + "]");
				} else {
					logger.error("解析EXCEL出现异常{}", e.getMessage());
					modelMap.put("message", "EXCEL解析格式错误：[" + e.getMessage() + "]");
				}
				printOut(modelMap, response);
				return;
				}
	    	modelMap.put("message", "EXCEL导入成功!");
	    	printOut(modelMap, response);
		
	}

	/**
	  * 
	  * <p>TODO 输出到客户端</p>
	  * @param modelMap
	  * @param response
	  * @author lkd
	 */
	@SuppressWarnings("all")
	private void printOut(Map modelMap, HttpServletResponse response) {
		PrintWriter out;
		try {
			response.setContentType("text/html;charset=UTF-8");
			out = response.getWriter();
			out.print(JSON.toJSONString(modelMap));
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("输出到客户端出现异常：{}", modelMap);
			modelMap.put("message", e.getCause().getMessage());
			return;
		}
	}


}
