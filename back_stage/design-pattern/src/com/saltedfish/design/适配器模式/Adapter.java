/**   
 * Copyright © 2017 公司名. All rights reserved.
 * 
 * @Title: Adapter.java 
 * @Prject: design-pattern
 * @Package: com.saltedfish.design.适配器模式 
 * @Description: TODO
 * @author: mjy   
 * @date: 2017年1月12日 上午10:21:28 
 * @version: V1.0   
 */
package com.saltedfish.design.适配器模式;

/** 
 * @ClassName: Adapter 
 * @Description: 适配器
 * 
 * 适配器模式:将一个类的接口转换成客户希望的另外一个接口，
 * 		   Adapter模式使得原本由于接口不见通而不能一起工作的那些类可以一起工作
 * 
 * 
 * 
 * @author: lkd
 * @date: 2017年1月12日 上午10:21:28  
 */
public class Adapter extends Source implements Targetable{

	@Override
	public void method2() {
		System.err.println("this is targetable method2...");
	}
	



	
	
}
