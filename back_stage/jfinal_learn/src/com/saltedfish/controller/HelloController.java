package com.saltedfish.controller;
/**   
 * Copyright © 2016 公司名. All rights reserved.
 * 
 * @Title: HelloController.java 
 * @Prject: jfinal_demo
 * @Package: com.saltedfish.controller 
 * @Description: TODO
 * @author: lkd   
 * @date: 2016年6月30日 下午3:56:06 
 * @version: V1.0   
 */


import com.jfinal.core.Controller;

/**
 * @ClassName: HelloController
 * @Description: TODO
 * @author: lkd
 * @date: 2016年6月30日 下午3:56:06
 */
public class HelloController extends Controller{
	public void index() {
		renderText("Hello JFinal World.");
	}
}
