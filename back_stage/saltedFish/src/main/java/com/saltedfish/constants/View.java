package com.saltedfish.constants;

/**
 * <p>=
 * ====视图=====
 * </p>
 * 
 * @author lkd
 *
 */
public interface View {
	// =============================权限相关======================================
	public static final String INDEX = "/index"; // 首页
	public static final String _403 = "/security/403";// 没有权限跳转页面
	public static final String LOGIN = "/login"; // 登陆页面
	public static final String TIME_OUT_VIEW = "/security/timeout";  // session过期页面
	public static final String WELCOME_VIEW = "/welcome";

	// =============================管理员管理===================================
	public static final String ADMIN_LIST_VIEW = "/admin/admin-list";
	public static final String ADMIN_ADD_VIEW = "/admin/admin-add";
	public static final String ADMIN_UPDATE_VIEW = "/admin/admin-update";
	public static final String AMDIN_RES_VIEW = "/admin/admin-resource-query";

	// =============================角色管理===================================
	public static final String ROLE_ADD_VIEW = "/role/role-add";
	public static final String ROLE_LIST_VIEW = "/role/role-list";
	public static final String ROLE_UPDATE_VIEW = "/role/role-update";

	// =============================资源管理===================================
	public static final String RESOURCE_LIST_VIEW = "/resource/resource-list";
	public static final String RESOURCE_ADD_VIEW = "/resource/resource-add";
	public static final String RESOURCE_UPDATE_VIEW = "/resource/resource-update";

}
