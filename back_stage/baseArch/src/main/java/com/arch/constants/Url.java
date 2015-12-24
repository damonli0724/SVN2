package com.arch.constants;
/**
 * <p>=====请求=====</p>
 * @author lkd
 *
 */
public interface Url {
	//===================登陆注册权限相关==============================
	public static final String INDEX = "index"; // 首页
	public static final String _403 = "403";// 没有权限跳转页面
	public static final String LOGIN = "login"; // 登陆页面
	public static final String LOING_OUT = "loginout";  //退出
	public static final String TIME_OUT_URL = "timeout";  //session失效
	//==============================================================
	public static final String RANDORM_CODE = "login/randomCode";//获取随机验证码
	public static final String  TURN_WELCOME = "welcome";
}
