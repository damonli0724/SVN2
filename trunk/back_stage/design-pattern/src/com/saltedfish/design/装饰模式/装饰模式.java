/**   
 * Copyright © 2017 公司名. All rights reserved.
 * 
 * @Title: 装饰模式.java 
 * @Prject: design-pattern
 * @Package: com.saltedfish.design.装饰模式 
 * @Description: TODO
 * @author: mjy   
 * @date: 2017年1月13日 下午4:37:59 
 * @version: V1.0   
 */
package com.saltedfish.design.装饰模式;

/** 
 * @ClassName: 装饰模式 
 * @Description: 
 *
 *装饰器模式的应用场景：
 * 1、需要扩展一个类的功能。
 * 2、动态的为一个对象增加功能，而且还能动态撤销。（继承不能做到这一点，继承的功能是静态的，不能动态增删。）
 *
 *
 * @author: mjy
 * @date: 2017年1月13日 下午4:37:59  
 */
public class 装饰模式 {
	public static void main(String[] args) {
		Sourceable source = new Decorator(new Source());
		source.method();
		
	}

}
interface  Sourceable {
	
	public void method();
	
} 

class Source implements Sourceable{

	@Override
	public void method() {
		System.err.println("this is original method...");
	}
}

class Decorator implements Sourceable{
	
	private Source source ; 
	
	public Decorator(Source so) {
		this.source = so;
	}

	@Override
	public void method() {
		System.err.println("增加的功能11111");
		source.method();
		System.err.println("增加的功能22222");
	}
	
	
} 


