/**   
 * Copyright © 2017 公司名. All rights reserved.
 * 
 * @Title: FactoryTest.java 
 * @Prject: design-pattern
 * @Package: com.saltedfish.design.普通工厂模式 
 * @Description: TODO
 * @author: mjy   
 * @date: 2017年1月9日 上午10:38:31 
 * @version: V1.0   
 */
package com.saltedfish.design.普通工厂模式;

/** 
 * @ClassName: FactoryTest 
 * @Description: 
 * *****************************************************************************
 * 
 * 普通工厂模式，比如发短信，和发邮件 ，发送抽取为通用接口，然后根据不同的类型，通过工厂返回接口对象
 *
 * *****************************************************************************
 * 
 * 
 * @author: mjy
 * @date: 2017年1月9日 上午10:38:31  
 */
public class FactoryTest {
	public static void main(String[] args) {
		SendFactory fatory = new SendFactory();
		
		Sender  sender = fatory.produce("mail");
		sender.send();
		
		sender=fatory.produce("sms");
		sender.send();  
		          
		sender=fatory.produce("xxx");
		sender.send();
	}
}
