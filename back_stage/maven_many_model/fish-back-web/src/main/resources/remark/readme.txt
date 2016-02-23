目录结构

包:  
	cmd  		访问请求javabean 
	constants   常量
	controller  跳转层
	dto			data traslation Object  数据传输层
	entitiy	 	数据库实体类
	mapper		方法与数据库访问接口
	service		服务层
	utils		工具包
	exception	自定义异常
	
文件
	log			日志
	mybatis		mybatis 配置文件
	remark		备注
	security	spring security 配置文件
	spring	 	spring 配置文件
	springdb	spring-database 配置文件
	springweb	controller配置文件
	sqlmap		数据库映射文件
	
//------------------------------------------------
maven 分模块 开发

fish-data       数据
fish-back-web  	后台跳转层
fish-dao 		数据访问层
fish-service    服务层
fish-utils		工具层
fish-parent     父层(用来做聚合和依赖管理)	
	

fish-data       数据
fish-app-web  	APP接口跳转层
fish-dao 		数据访问层
fish-service    服务层
fish-utils		工具层
fish-parent     父层(用来做聚合和依赖管理)		
	