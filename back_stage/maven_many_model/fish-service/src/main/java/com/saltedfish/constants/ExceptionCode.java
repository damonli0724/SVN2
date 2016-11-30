/*******************************************************************************
 * Project   : portal-common
 * Class Name: com.yyq.cloud.portal.common.constant.ExceptionCode
 * Created By: Jonathan 
 * Created on: 2014-6-24 上午1:33:23
 * Copyright © 2008-2014 NATIE All rights reserved.
 ******************************************************************************/
package com.saltedfish.constants;

/**
 * <P>异常编码常量</P>
 * @author Jonathan
 */
public class ExceptionCode {

	/**
	 * 异常码前缀
	 */
	final public static String EXCEPTION_CODE_REFIX = "ex_code_";

	/**
	 * 查询模版错误
	 */
	final public static String TEMPLATE_NOT_TO_FIND = EXCEPTION_CODE_REFIX + "template_not_find";

	/**
	 * 模版内容编码错误
	 */
	final public static String TEMPLATE_ENCODING_ERROR = EXCEPTION_CODE_REFIX + "template_encoding_error";

	/**
	 * 初始化模版错误
	 */
	final public static String TEMPIATE_FAILED_TO_INIT = EXCEPTION_CODE_REFIX + "template_failed_to_init";

	/**
	 * 连接短信网关异常
	 */
	final public static String SMS_GATEWAY_FAILED_TO_CONNECT = EXCEPTION_CODE_REFIX + "sms_gateway_failed_to_connect";;

	/**
	 * 注册手机号码重复
	 */
	final public static String MOBILE_EXISTING = EXCEPTION_CODE_REFIX + "mobile_existing";

	/**
	 * 两次密码不一致
	 */
	final public static String PASSWORD_INCONSISTENT = EXCEPTION_CODE_REFIX + "password_inconsistent";

	/**
	 * 注册年龄不符要求
	 */
	final public static String UNDERAGE = EXCEPTION_CODE_REFIX + "underage";

	/**
	 * 昵称已经存在
	 */
	final public static String NICKNAME_EXISTING = EXCEPTION_CODE_REFIX + "nickname_existing";

	/**
	 * 创建目录失败
	 */
	final public static String DIRECTORY_FAILED_TO_CREATE = EXCEPTION_CODE_REFIX + "directory_failed_to_create";

	/**
	 * 文件已经存在
	 */
	final public static String FILE_EXISTING = EXCEPTION_CODE_REFIX + "file_existing";

	/**
	 * 保存文件失败
	 */
	final public static String FILE_FAILED_TO_SAVE = EXCEPTION_CODE_REFIX + "file_failed_to_save";

	/**
	 * 验证码手机不匹配
	 */
	final public static String AUTH_MOBILE_NOT_MATCH = EXCEPTION_CODE_REFIX + "auth_mobile_not_match";

	/**
	 * 验证码不正确
	 */
	final public static String AUTH_CODE_INCORRECT = EXCEPTION_CODE_REFIX + "auth_code_incorrect";

	/**
	 * 验证码过期
	 */
	final public static String AUTH_CODE_EXPIRED = EXCEPTION_CODE_REFIX + "auth_code_expired";

	/**
	 * 加解密错误
	 */
	final public static String ENCRYPTION_ERROR = EXCEPTION_CODE_REFIX + "encryption_error";

	/**
	 * 更新邻座邀请错误
	 */
	final public static String INVITE_UPDATE = EXCEPTION_CODE_REFIX + "invite_failed_to_update";
	/**
	 * 时间解析异常
	 */
	final public static String TIME_PARSE_ERROR = EXCEPTION_CODE_REFIX + "time_parse_error";
	/**
	 * pagesize不能为0
	 */
	final public static String PAGESIZE_ZERO_INCORRECT = EXCEPTION_CODE_REFIX + "page_size_incorrect";

	/**
	 * 话题不存在
	 */
	public static final String TOPIC_FAILED_TO_FIND = EXCEPTION_CODE_REFIX + "topic_failed_to_find";
	/**
	 * 用户喜欢关系已经存在
	 */
	public static final String RELATIONSHIP_EXISTING_LIKE = EXCEPTION_CODE_REFIX + "relationship_exsting_like";
	/**
	 * 用户不喜欢关系已经存在
	 */
	public static final String RELATIONSHIP_EXISTING_NO_LIKE = EXCEPTION_CODE_REFIX + "relationship_exsting_no_like";

	/**
	 * 缺少头像
	 */
	public static final String ACCOUNT_AVATAR_MISSING = EXCEPTION_CODE_REFIX + "account_avatar_missing";

	/**
	 * 无法读取头像文件
	 */
	public static final String ACCOUNT_AVATAR_FAILED_TO_READ = EXCEPTION_CODE_REFIX + "account_avatar_failed_to_read";

	/**
	 * 无法读取相册文件
	 */
	public static final String ACCOUNT_ALBUM_MISSING = EXCEPTION_CODE_REFIX + "account_album_failed_to_read";

	/**
	 * 无法读取动态图片
	 */
	public static final String ACCOUNT_UPDATE_MISSING = EXCEPTION_CODE_REFIX + "account_update_failed_to_read";
}
