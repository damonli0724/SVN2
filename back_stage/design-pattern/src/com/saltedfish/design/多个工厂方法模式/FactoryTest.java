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
package com.saltedfish.design.多个工厂方法模式;

/** 
 * @ClassName: FactoryTest 
 * @Description: 
 * *****************************************************************************
 * 
 * 多个工厂模式，在工厂里面有多个方法定义，返回多个具体实例
 *
 * *****************************************************************************
 * 
 * 
 * @author: lkd
 * @date: 2017年1月9日 上午10:38:31  
 */
public class FactoryTest {
	public static void main(String[] args) {
		
		SendFactory factory  = new SendFactory();
		factory.produceMail().send();
		factory.produceSMs().send();
	}
}
