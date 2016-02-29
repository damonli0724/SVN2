package com.saltedfish.controller.constants;

/**
 * <p>=====请求=====</p>
 * @author lkd
 *
 */
public interface Url {
	
	
	//极光推送
	public static final String JPUSH_PUSH_MESSAGE ="unsecured/jpush/pushMessage";
	//短信发送
	public static final String SMS_SEND_MESSAGE="unsecured/sms/sendMessage";
	//权限过滤测试
	public static final String UNSECURED_TEST = "unsecured/test";
	//权限测试
	public static final String  SECURED_TEST = "test/test";
	//全局异常 和 验证测试
	public static final String  EXCEPTION_VALIDATE_TEST = "unsecured/validate/exception/test";
	

}
