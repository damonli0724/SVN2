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


import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;

/**
 * @ClassName: UserController
 * @Description: TODO
 * @author: lkd
 * @date: 2016年6月30日 下午3:56:06  
 */
public class UserController extends Controller{
	
	@ActionKey("/login")
	public void login(){
		render("/login.html");
	}
	
	
	//    /jfinal_learn/login/submit/2-5-9-N8
	@ActionKey("/login/submit")
	public void loginSubmit(){/* 
		//-----------------------------------------------------------getpara  获取参数
		String account = getPara("user.account");
		String password =getPara("user.password");
		
		System.err.println("account："+account);
		System.err.println("password："+password);
		
		int a = getParaToInt(0);// 获得2
		String b = getPara(1);//获得5
		int c=getParaToInt(3);//约定 n或者N+数字 为负数 获得-8
		
		System.err.println(a);
		System.err.println(b);
		System.err.println(c);
		//-----------------------------------------------------------getModel/getBean 获取对象，即多个参数封装成对象  页面为 对象名称.属性  
		User  user = getBean(User.class);
		System.err.println("user"+user); 
		
		User2 user2 = getModel(User2.class);
		System.err.println("user2"+user2);
		//-------------------------------------------------------------页面赋值
		setAttr("user", user);
		
		render("/index.jsp"); 
	*/}
	
}
