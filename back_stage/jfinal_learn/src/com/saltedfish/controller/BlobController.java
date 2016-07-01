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
import com.jfinal.plugin.activerecord.Page;
import com.saltedfish.common.model.Blog;

/**
 * @ClassName: HelloController
 * @Description: TODO
 * @author: lkd
 * @date: 2016年6月30日 下午3:56:06
 */
public class BlobController extends Controller{
	
	
	public void index() {
//		setAttr("blogPage", Blog.me.paginate(getParaToInt(0, 1), 10));
		Page<Blog> page = Blog.me.paginate(1/*getParaToInt(0, 1)*/, 10);
		
//		list.getList()
//		list.getTotalRow()
		
		
		setAttr("page", page);
		
		render("/blob_list.jsp"); 
	}
	
	
	
}
