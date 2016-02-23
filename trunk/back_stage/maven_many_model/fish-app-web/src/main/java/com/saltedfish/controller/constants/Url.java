package com.saltedfish.controller.constants;

/**
 * <p>=====请求=====</p>
 * @author lkd
 *
 */
public interface Url {
	// 登陆注册权限相关
	public static final String INDEX = "background/index"; // 首页
	public static final String _403 = "background/403";// 没有权限跳转页面
	public static final String LOGIN = "background/login"; // 登陆页面
	public static final String LOGIN_CHECK = "background/check";// 账户登录校验
	public static final String LOING_OUT = "background/loginout";  // 退出
	public static final String TIME_OUT_URL = "background/timeout";  // session失效
	public static final String RANDORM_CODE = "background/randomCode";// 获取随机验证码
	public static final String TURN_WELCOME = "background/welcome"; // 首页

	// 管理员管理
	public static final String ADMIN_LIST_PAGE = "background/admin/list/page";// 管理员列表页面
	public static final String ADMIN_LIST_DATA = "background/admin/list/page/data";// 管理员列表页面数据
	public static final String ADMIN_ADD_PAGE = "background/admin/add/page"; // 管理员添加页面
	public static final String ADMIN_ADD_DATA = "background/admin/add/page/data"; // 管理员添加
	public static final String ADMIN_ENABLED_UPDATE = "background/admin/enabled/update";  // 管理员禁用/启动
	public static final String ADMIN_DELETE = "background/admin/delete"; // 删除管理员
	public static final String ADMIN_UPDATE_PAGE = "background/admin/update/page"; // 管理员修改页面
	public static final String ADMIN_CHECK_ONLY_ACCOUNT_QUERY = "backgrond/admin/check/query"; // 判断用户唯一
	public static final String ADMIN_RES_SHOW = "background/admin/res/page"; // 查看用户拥有的权限

	// 角色管理
	public static final String ROLE_ADD_PAGE = "background/role/add/page";  // 角色添加页面
	public static final String ROLE_ADD_DATA = "background/role/add/data";  // 角色添加
	public static final String ROLE_LIST_PAGE = "background/role/list/page"; // 角色列表页面
	public static final String ROLE_UPDATE_PAGE = "background/role/update/page"; // 角色修改页面
	public static final String ROLE_UPDATE_DATA = "background/role/update/data"; // 角色修改
	public static final String ROLE_DELETE_DATA = "background/role/delete/data"; // 角色删除

	// 资源管理
	public static final String RESOURCE_ADD_PAGE = "background/resource/add/page";// 资源添加页面
	public static final String RESOURCE_UPDATE_PAGE = "background/resource/update/page";// 资源添加页面
	public static final String RESOURCE_TREE_DATA = "background/resource/tree/data"; // 加载tree资源
	public static final String RESOURCE_LIST_PAGE = "background/resource/list/page"; // 资源列表页面
	public static final String RESOURCE_LIST_DATA = "background/resource/list/page/data";// 资源列表页面数据
	public static final String RESOURCE_ADD_DATA = "background/resource/add/data";   // 资源添加
	public static final String RESOURCE_LOAD_BY_PARENTID = "background/resource/query/parentId"; // 根据parentId查询加载资源
	public static final String RESOURCE_DELETE = "background/resource/delete"; // 删除资源
	public static final String RESOURCE_UPDATE_DATA = "background/resource/update/data";// 修改资源
	
	//极光推送
	public static final String JPUSH_PUSH_MESSAGE ="jpush/pushMessage";

}
