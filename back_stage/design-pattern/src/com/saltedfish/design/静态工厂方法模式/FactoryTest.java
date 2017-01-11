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
package com.saltedfish.design.静态工厂方法模式;

/** 
 * @ClassName: FactoryTest 
 * @Description: 
 * *****************************************************************************
 * 
 * 静态工厂模式，在工厂里面有多个方法定义，返回多个具体实例,(将工厂的方法设定为静态)
 * 
 * 总体来说，工厂模式适合：凡是出现了大量的产品需要创建，并且具有共同的接口时，可以通过工厂方法模式进行创建。在以上的三种模式中，第一种如果传入的字符串有误，不能正确创建对象，
 * 第三种相对于第二种，不需要实例化工厂类，所以，大多数情况下，我们会选用第三种——静态工厂方法模式。
 *
 * *****************************************************************************
 * 
 * 
 * @author: mjy
 * @date: 2017年1月9日 上午10:38:31  
 */
public class FactoryTest {
	public static void main(String[] args) {
		SendFactory.produceMail().send();
		SendFactory.produceSMs().send();
	}
}
