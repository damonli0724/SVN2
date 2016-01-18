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
	// =========================================================================

	// =============================管理员管理===================================
	public static final String ADMIN_LIST_VIEW = "/admin/admin-list";
	public static final String ADMIN_ADD_VIEW = "/admin/admin-add";
	public static final String ADMIN_UPDATE_VIEW = "/admin/admin-update";

	// =============================角色管理===================================
	public static final String ROLE_ADD_VIEW = "/role/role-add";
	public static final String ROLE_LIST_VIEW ="/role/role-list";
	

}
