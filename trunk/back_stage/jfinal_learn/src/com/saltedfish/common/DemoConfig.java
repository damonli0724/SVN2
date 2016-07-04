/**   
 * Copyright © 2016 公司名. All rights reserved.
 * 
 * @Title: DemoConfig.java 
 * @Prject: jfinal_demo
 * @Package: com.saltedfish.jfinal.common.config 
 * @Description: TODO
 * @author: mjy   
 * @date: 2016年6月30日 下午3:51:21 
 * @version: V1.0   
 */
package com.saltedfish.common;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;
import com.saltedfish.common.model._MappingKit;
import com.saltedfish.controller.HelloController;
import com.saltedfish.controller.IndexController;
import com.saltedfish.controller.UserController;
import com.saltedfish.controller.blog.BlobController;

/**
 * @ClassName: DemoConfig
 * @Description: jfinal配置
 * @author: lkd
 * @date: 2016年6月30日 下午3:51:21
 */
public class DemoConfig extends JFinalConfig {

	// 此方法用来配置 JFinal 常量值，如开发模式常量 devMode 的配置，默认视图类型 ViewType.
	public void configConstant(Constants me) { 
//     	me.setViewType(ViewType.JSP);
     	//me.setViewType(ViewType.FREE_MARKER); 
     	PropKit.use("a_little_config.txt");
		me.setDevMode(PropKit.getBoolean("devMode", false));
		// 加载配置文件
	}

	// 访问路由，根据名称，跳转到对应的Controller里面，访问index方法。
	public void configRoute(Routes me) {
		me.add("/hello", HelloController.class);
//		me.add("/", UserController.class);
		
		//博客管理跳转层
		me.add("/blog",BlobController.class);
		//首页跳转层 
		me.add("/index",IndexController.class); 
	} 

	public static C3p0Plugin createC3p0Plugin() {
		return new C3p0Plugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
		
	}
	
	// 配置插件
	public void configPlugin(Plugins me) {
		// 配置C3p0数据库连接池插件
		C3p0Plugin C3p0Plugin = createC3p0Plugin();
		me.add(C3p0Plugin);

		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(C3p0Plugin);
		me.add(arp);

		// 所有配置在 MappingKit 中搞定
		 _MappingKit.mapping(arp);
	}


	public void configInterceptor(Interceptors me) {
	}

	public void configHandler(Handlers me) {
	}
}
