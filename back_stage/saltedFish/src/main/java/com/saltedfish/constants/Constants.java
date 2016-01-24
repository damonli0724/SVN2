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
	public static final String R_STATUS_SUCCESS = "1"; // 状态 成功
	public static final String R_STATUS_FAILTURE = "0"; // 状态失败

	public static final Integer RESOURCE_MENU = 0;  // 主菜单
	public static final Integer RESOURCE_CHID_MENU = 1; // 子菜单
	public static final Integer RESOURCE_CHILD_BTN = 2; // 子菜单中的按钮
	public static final Integer RESOURCE_TREE_ROOT_ID = 0;//权限树根节点ID

}
