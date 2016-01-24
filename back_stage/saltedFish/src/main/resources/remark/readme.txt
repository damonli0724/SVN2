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
删除资源-->删除资源表数据，删除 角色资源表关联数据
删除角色-->删除该角色表数据，删除角色资源关联表数据，修改拥有该角色的用户 状态为失效（这种只针对于1对1关联关系）
删除用户-->删除用户数据
	
	
	
	
	