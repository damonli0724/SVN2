/**
 * 
 */
package com.saltedfish.controller;

import com.jfinal.core.Controller;

/**
 * @author Administrator
 *
 */
public class IndexController extends Controller {
	
	//首页
	public void index(){
	render("index.html");	
	}

}
