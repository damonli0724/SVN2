/**   
 * Copyright © 2017 公司名. All rights reserved.
 * 
 * @Title: Singleton.java 
 * @Prject: design-pattern
 * @Package: com.saltedfish.design.单例模式 
 * @Description: TODO
 * @author: mjy   
 * @date: 2017年1月10日 上午10:07:09 
 * @version: V1.0   
 */
package com.saltedfish.design.单例模式;

/** 
 * @ClassName: Singleton 
 * @Description: 单例模式
 * 
 * 	单例对象（Singleton）是一种常用的设计模式。在Java应用中，单例对象能保证在一个JVM中，该对象只有一个实例存在。这样的模式有几个好处：
 *  1、某些类创建比较频繁，对于一些大型的对象，这是一笔很大的系统开销。
 *  2、省去了new操作符，降低了系统内存的使用频率，减轻GC压力。
 *  3、有些类如交易所的核心交易引擎，控制着交易流程，如果该类可以创建多个的话，系统完全乱了。（比如一个军队出现了多个司令员同时指挥，肯定会乱成一团），所以只有使用单例模式，才能保证核心交易服务器独立控制整个流程。
 * 
 * @author: lkd
 * @date: 2017年1月10日 上午10:07:09  
 */

public class SingletonTest{
	
	public static void main(String[] args) {
			
		SingletonThreadTest[] threads = new SingletonThreadTest[10];
			 
			for (int i = 0; i < 10; i++) {
				 threads[i] = new SingletonThreadTest();
			}
			for (int i = 0; i < 10; i++) {
				threads[i].start();
			}
		}
}




//饱汉模式
class Singleton{
	
	/*持有私有静态实例，防止被引用，此处赋值为null，目的是实现延迟加载 */  
	private static Singleton instance = null;
	
	 /* 私有构造方法，防止被实例化 */  
	private Singleton() {
	}
	
	//此处存在线程安全问题 
//	public  static Singleton getInstance(){
//		if(instance ==null ){
//			instance = new Singleton();
//		}
//			return instance;
//	}
	// 加上sync 关键字，可以保证了同步，但是锁整个方法消耗性能（因为也就创建实例的时候加锁，以后都不需要了，但是每次调用的时候都要用到这个加锁方法，影响性能），所以使用同步代码块
//	public synchronized static Singleton getInstance(){
//		if(instance ==null ){
//			instance = new Singleton();
//		}
//			return instance;
//	}
	// 使用同步代码也会导致线程不安全，因为 new Singleton 和 return instance 应该是一起的，破坏了原子性，所以一样有问题
//	public  static Singleton getInstance(){
//		if(instance ==null ){
//			synchronized (Singleton.class) {
//				instance = new Singleton();
//			}
//		}
//		return instance;
//	}
	
	//Double Check Locking 双检查锁机制（推荐） ，即可保证线程安全性-->这样写还是有时候会出现问题，内存模型允许所谓的“无序写入”。。，（具体请看 java内存模型，把instance 加入volitate 就变成了线程安全）
	public  static Singleton getInstance(){
		if(instance ==null ){
			synchronized (Singleton.class) {
				if(instance==null){
					instance = new Singleton();
				}
			}
		}
		return instance;
	}
	
	
	
	//使用 静态内部类的方式 ,, 这样就可以避免线程安全问题，当要使用到 这个单利的时候，才会去加载静态内部类，而且所有线程都在外面等待，单例对象new出来
	public static Singleton getInstance2(){
		return  InnerClass.instance;
	}
	
    static class InnerClass{
		private  static  Singleton  instance =  new Singleton();
	}
	
	
	 /* 如果该对象被用于序列化，可以保证对象在序列化前后保持一致 */  
    public Object readResolve() {  
        return instance;  
    }  
	
}

//**********************************************************************************************************************//

//饥汉模式
class Singleton2{
	    //类加载时候就创建实体类
		private static Singleton2  sing= new Singleton2();
		
		//私有化构造器		
		private Singleton2(){
			
		}
		
		public static Singleton2 getInstance(){
			return sing;
		}
}

class SingletonThreadTest   extends Thread{
	
	
	@Override
	public void run() {
		System.err.println(Singleton.getInstance().hashCode());
	}
	
}





