/**   
 * Copyright © 2017 公司名. All rights reserved.
 * 
 * @Title: Test.java 
 * @Prject: design-pattern
 * @Package: com.saltedfish.design.适配器模式 
 * @Description: TODO
 * @author: mjy   
 * @date: 2017年1月12日 上午10:26:20 
 * @version: V1.0   
 */
package com.saltedfish.design.适配器模式;

/** 
 * @ClassName: Test  
 * @Description: TODO
 * @author: lkd
 * @date: 2017年1月12日 上午10:26:20  
 */
public class Test {
	
	
	/**
	 *  
	 * 适配器模式:将一个类的接口转换成客户希望的另外一个接口，
     *		   Adapter模式使得原本由于接口不见通而不能一起工作的那些类可以一起工作
	 *
	 * 适配器模式分三个角色
	 * 
	 *　●　　目标(Target)角色：这就是所期待得到的接口。注意：由于这里讨论的是类适配器模式，因此目标不可以是类。
　           *　●　　源(Adapee)角色：现在需要适配的接口。
　           *　●　　适配器(Adaper)角色：适配器类是本模式的核心。适配器把源接口转换成目标接口。显然，这一角色不可以是接口，而必须是具体类。
	 * 
	 * Targetable  这个类就是 所期待得到的接口
	 * Source  这个类 是源来的角色， 比如他只有method1 功能，现在 我想要 method2功能，而且不想改动原来的代码
	 * 
	 * Adapter 适配器角色， 继承Source角色，便有了method1方法，然后 实现method2功能，这样子  适配器就同时拥有了两种方法。
	 * 
	 * 也许你会说，为什么不直接在“源”中直接添加方法，我的理解是，适配是为了实现某种目的而为一个源类暂时性的加上某种方法，所以不能破坏原类的结构。同时不这么做也符合Java的高内聚，低耦合的原理。既然不能直接加，接着我们就来说该怎么来实现为人这个“源”添加一个方法，而又不破坏“源”的本身结构。
	 */
	public static void main(String[] args) {
		 Targetable target = new Adapter();  
	        target.method1();  
	        target.method2();  
	}

}
