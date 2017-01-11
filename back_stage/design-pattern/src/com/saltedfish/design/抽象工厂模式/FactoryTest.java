/**   
 * Copyright © 2017 公司名. All rights reserved.
 * 
 * @Title: FactoryTest.java 
 * @Prject: design-pattern
 * @Package: com.saltedfish.design.抽象工厂模式 
 * @Description: TODO
 * @author: mjy   
 * @date: 2017年1月9日 下午2:20:09 
 * @version: V1.0   
 */
package com.saltedfish.design.抽象工厂模式;

/** 
 * @ClassName: FactoryTest 
 * @Description: TODO
 * *****************************************************************************
 * 
 * 抽象工厂模式，其实这个模式的好处就是，如果你现在想增加一个功能：发及时信息，则只需做一个实现类，实现Sender接口，同时做一个工厂类，实现Provider接口，就OK了，无需去改动现成的代码。这样做，拓展性较好！
 *
 * *****************************************************************************
 * @author: lkd
 * @date: 2017年1月9日 下午2:20:09  
 */
public class FactoryTest {
	
	public static void main(String[] args) {
		Provider mailProvider = new SendMailFactory();
		Sender mailSender  = mailProvider.produce();
		mailSender.send();
		
		Provider smsProvider = new  SendSmsFactory();
		Sender smsSender  = smsProvider.produce();
		smsSender.send();
	}

}
