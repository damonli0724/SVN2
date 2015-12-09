package com.arch.constants;

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
	public static final String TIME_OUT_VIEW = "/security/timeout";  //session过期页面
	//=========================================================================
}
