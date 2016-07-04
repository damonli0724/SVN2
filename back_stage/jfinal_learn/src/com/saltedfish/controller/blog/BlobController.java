package com.saltedfish.controller.blog;
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


import com.jfinal.aop.Before;
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
		Page<Blog> page = Blog.me.paginate(getParaToInt(0, 1), 10);
		setAttr("blogPage", page);
		render("blog.html");
	}
	 
	public void add(){
		render("add.html");
	}
	
	@Before(BlogValidator.class)
	public void save(){
		getModel(Blog.class).save();
		redirect("/blog");
	}
	
	 
	public void edit(){  
		setAttr("blog", Blog.me.findById(getPara("id")));  
	}
	
	@Before(BlogValidator.class)
	public void update(){
		getModel(Blog.class).update(); 
		redirect("/blog");
	}
	
	public void delete(){
		Blog.me.deleteById(getParaToInt());
		redirect("/blog");
	}
	
	
	
}
