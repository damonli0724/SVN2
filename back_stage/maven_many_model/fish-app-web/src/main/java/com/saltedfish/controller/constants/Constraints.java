/*******************************************************************************
 * Project   : portal
 * Class Name: com.yyq.portal.web.constant.Constants
 * Created By: Jonathan 
 * Created on: 2014-8-28 下午11:18:18
 * Copyright © 2008-2014 NATIE All rights reserved.
 ******************************************************************************/
package com.saltedfish.controller.constants;

/**
 * <P>TODO</P>
 * @author Jonathan
 */
public interface Constraints {

	//任何非空白字符
	String REGEXP_ANY_NOT_BLANK_CHAR = "(\\S){6,20}";
	// 手机号码
	String REGEXP_MOBILE_PHONE = "^1[3-9]\\d{9}$";
	// 手机号码(允许为空)
	String REGEXP_MOBILE_PHONE_NULLABLE="^\\s*$ ||^1[3-9]\\d{9}$";
	// 邮箱
	String REGEXP_EMAIL = "\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
	//密码
	String REGEXP_PASSWORD ="((?=.*[0-9])(?=.*[a-z]).{6,20})";
	// 身份证件类型正则
	String REGEXP_CERTIFICATE_TYPE= "//(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)//";
	// 日期
	String REGEXP_DATE = "\\d{8}";
	// 金额最小值
	String REGEXP_AMOUNT_MIN = "0.01";
	// 金额最大值
	String REGEXP_AMOUNT_MAX = "9999999999999.99";
	
	// 日期 
	// String REGEXP_DATE =
	// "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";
	// 时间(2014-12-20 22:15)
	String REGEXP_TIME = "((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)) (20|21|22|23|[0-1]?\\d):[0-5]?\\d$";

	String REGEXP_VERIFICATION_CODE="^\\d{4}$";
	//姓名
	String REGEXP_NAME = "[\u4E00-\u9FA5]{2,4}" ;
	//正整数
	String REGEXP_INT = "^[0-9]*$" ;
	//小于9位的数字
	String REGEXP_INT_N = "^\\d{0,9}$" ;
	//昵称
	String REGEXP_NICK_NAME = "^[(a-zA-Z0-9\\d\\u4e00-\\u9fa5)]{2,20}$";
}
