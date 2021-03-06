/**   
 * Copyright © 2017 公司名. All rights reserved.
 * 
 * @Title: SendFactory.java 
 * @Prject: design-pattern
 * @Package: com.saltedfish.design.普通工厂模式 
 * @Description: TODO
 * @author: mjy   
 * @date: 2017年1月9日 上午10:35:51 
 * @version: V1.0   
 */
package com.saltedfish.design.多个工厂方法模式;

/** 
 * @ClassName: SendFactory 
 * @Description: TODO
 * @author: mjy
 * @date: 2017年1月9日 上午10:35:51  
 */
public class SendFactory {
		public  Sender  produceMail(){
			return new  MailSender();
		}
		
		public  Sender  produceSMs(){
			return new  SmsSender();
		}
	
}
