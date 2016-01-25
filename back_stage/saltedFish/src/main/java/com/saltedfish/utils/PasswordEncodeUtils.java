/*******************************************************************************
 * Project   : saltedFish
 * Class Name: com.saltedfish.utils.PasswordEncodeUtils
 * Created By: LKD 
 * Created on: 2016年1月25日 下午12:18:08
 * Copyright © 2013-2014 saltedFish All rights reserved.
 ******************************************************************************/
package com.saltedfish.utils;



/**
 * <P>用户密码加密类</P>
 * @author LKD
 */
public class PasswordEncodeUtils {

	/**
	 *	--加密规则，先加密密码，然后加上登录名 再加密 然后加上一个key 再加密--
	 * <p>加密</p>
	 * @param password
	 * @param userName
	 * @param passwrodKey
	 * @return
	 * @author LKD
	 */
	public static String encode(String password, String userName, String passwrodKey) {
		return MD5Util.MD5(MD5Util.MD5(MD5Util.MD5(password.trim()) + userName.trim()) + passwrodKey.trim());
	}

}
