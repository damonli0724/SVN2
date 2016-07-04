/**   
 * Copyright © 2016 公司名. All rights reserved.
 * 
 * @Title: xxx.java 
 * @Prject: jfinal_learn
 * @Package: com.saltedfish.controller.blog 
 * @Description: TODO
 * @author: mjy   
 * @date: 2016年7月4日 下午1:40:13 
 * @version: V1.0   
 */
package com.saltedfish.controller.blog;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;
import com.saltedfish.common.model.Blog;

/**
 * 
 * @ClassName: BlogValidator 
 * @Description: BlogValidator
 * @author: lkd
 * @date: 2016年7月4日 下午1:41:12
 */
public class BlogValidator extends Validator {
	
	protected void validate(Controller controller) { 
		validateRequiredString("blog.title", "titleMsg", "请输入Blog标题!");
		validateRequiredString("blog.content", "contentMsg", "请输入Blog内容!");
	}
	
	protected void handleError(Controller controller) { 
		controller.keepModel(Blog.class); 
		String actionKey = getActionKey();
		if (actionKey.equals("/blog/save"))
			controller.render("add.html");
		else if (actionKey.equals("/blog/update"))
			controller.render("edit.html");
	}
}