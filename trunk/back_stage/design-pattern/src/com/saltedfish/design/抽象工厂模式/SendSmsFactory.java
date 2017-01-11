/**   
 * Copyright © 2017 公司名. All rights reserved.
 * 
 * @Title: SendSmsFactory.java 
 * @Prject: design-pattern
 * @Package: com.saltedfish.design.抽象工厂模式 
 * @Description: TODO
 * @author: mjy   
 * @date: 2017年1月9日 下午2:19:32 
 * @version: V1.0   
 */
package com.saltedfish.design.抽象工厂模式;

/** 
 * @ClassName: SendSmsFactory 
 * @Description: TODO
 * @author: mjy
 * @date: 2017年1月9日 下午2:19:32  
 */
public class SendSmsFactory implements Provider{

	@Override
	public Sender produce() { 
		return new SmsSender();
	}

}
