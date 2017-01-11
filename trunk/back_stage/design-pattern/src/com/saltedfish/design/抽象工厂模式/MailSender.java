/**   
 * Copyright © 2017 公司名. All rights reserved.
 * 
 * @Title: MailSender.java 
 * @Prject: design-pattern
 * @Package: com.saltedfish.design.普通工厂模式 
 * @Description: TODO
 * @author: mjy   
 * @date: 2017年1月9日 上午10:27:33 
 * @version: V1.0   
 */
package com.saltedfish.design.抽象工厂模式;

/** 
 * @ClassName: MailSender 
 * @Description: TODO
 * @author: lkd
 * @date: 2017年1月9日 上午10:27:33  
 */
public class MailSender implements Sender{

	@Override
	public void send() {
		System.err.println("this is  mailsender ....");
	}

	
	
}
