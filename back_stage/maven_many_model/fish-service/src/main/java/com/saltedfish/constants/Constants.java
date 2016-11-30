/*******************************************************************************
 * Project   : portal-back
 * Class Name: com.yyq.portal.back.constant.Constants
 * Created By: Jonathan 
 * Created on: 2014-6-24 下午2:53:02
 * Copyright © 2008-2014 NATIE All rights reserved.
 ******************************************************************************/
package com.saltedfish.constants;

/**
 * <P>常量接口</P>
 * @author lkd
 */
public interface Constants {

	// 获取spring security 上下文
	public static final String SPRING_SECURITY_CONTEXT = "SPRING_SECURITY_CONTEXT";
	// 获取spring security 异常
	public static final String SPRING_SECURITY_EXCEPTION = "SPRING_SECURITY_LAST_EXCEPTION";
	public static final String R_STATUS_SUCCESS = "1"; // 状态 成功
	public static final String R_STATUS_FAILTURE = "0"; // 状态失败

	public static final String PASSWORD_KEY = "XLlWePlTYe2l3vpPuQJMUGvUtJJwjqYQ";  // 密码加密用到的key

	//菜单
	public static final Integer RESOURCE_MENU = 0;  // 主菜单
	public static final Integer RESOURCE_CHID_MENU = 1; // 子菜单
	public static final Integer RESOURCE_CHILD_BTN = 2; // 子菜单中的按钮
	public static final Integer RESOURCE_TREE_ROOT_ID = 0;// 权限树根节点ID

	//账户是否冻结
	public static final String ACCOUNT_FREEZE = "freeze";
	public static final String ACCOUNT_FREEZE_Y = "0";
	public static final String ACCOUNT_FREEZE_N = "1";
	
	 //秒杀常量
	public static final String SEC_ALL_MONEY_ACCOUNT="redis:watch:seckill:account";//竞标总金额
	public static final String SEC_BID_USERS="redis:watch:seckill:bidUsers3";//中标人员集合
	
	//上传图片大小
	public static final int PROFILE_PHOTO_MAX_SIZE = 10000000;// 相册图片最大大小10M
	
	
}
