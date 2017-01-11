/**   
 * Copyright © 2017 公司名. All rights reserved.
 * 
 * @Title: sendMailFactory.java 
 * @Prject: design-pattern
 * @Package: com.saltedfish.design.抽象工厂模式 
 * @Description: TODO
 * @author: mjy   
 * @date: 2017年1月9日 下午2:18:08 
 * @version: V1.0   
 */
package com.saltedfish.design.抽象工厂模式;

/** 
 * @ClassName: sendMailFactory 
 * @Description: TODO
 * @author: mjy
 * @date: 2017年1月9日 下午2:18:08  
 */
public class SendMailFactory implements Provider{

	@Override
	public Sender produce() {
		return new MailSender();
	}

}
