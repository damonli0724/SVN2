/**   
 * Copyright © 2017 公司名. All rights reserved.
 * 
 * @Title: 简单工厂模式.java 
 * @Prject: desin-test
 * @Package: com.saltedfish.desgin.test2 
 * @Description: TODO
 * @author: lkd   
 * @date: 2017年1月16日 下午2:23:04 
 * @version: V1.0   
 */
package com.saltedfish.design;


/** 
 * @ClassName: 
 * @Description: TODO
 * @author: lkd
 * @date: 2017年1月16日 下午2:23:04  
 */
public class DesinTest {
	public static void main(String[] args) {
        //工厂模式分为 简单工厂，多方法工厂，抽象工厂，例子:流水线生产手机(手机又showMessage()方法),生产 android，ios手机
		//01.简单工厂：根据传入的数据类型，生产实力对象
		IMobile androidPhone = SimpleFactory.procdure("android");
		androidPhone.showMessage();
		//02.多方法工厂：一个工厂有多个方法，根据调用的方法生产实例
		IMobile  iosPhone =  ManyFactory.procdureIosPhone();
		iosPhone.showMessage();
		//03.抽象工厂:四个角色 抽象工厂，实际工厂， 抽象产品，实际产品 ，在实际工厂里面返回实际产品,之前的两种工厂模式，你现在生产第三种手机，都是需要改原来的代码的，而第三种，你则可以直接再写一个类，实现IMobile就可以了，符合java的高类聚，低耦合
		AbstractFactroy  androidFactory = new AndroidFactory();
		androidFactory.procdure().showMessage();
		//04.单例模式:两种，饱汉模式，饥饿模式
		FullSington sington = FullSington.getInstance4();
		//05.适配器模式:两种，类适配器，对象适配器, 角色:源角色，目标角色(接口)，适配器角色
		ITarget classAdapter =  new ClassAdapter();
		classAdapter.sayFrench();
		classAdapter.sayChinese(); 
		classAdapter.sayEnglish();
		
		ITarget objectAdapter = new ObjectAdapter(new People());
		objectAdapter.sayFrench();
		objectAdapter.sayChinese(); 
		objectAdapter.sayEnglish();
		
		//06.装饰模式,在不改变原来的方法前提上，装饰一波方法
		IDecorate decorate =  new Decorater(new  DecorateSource());
		decorate.print();
		
		//07.代理模式（为其他对象提供一种代理以控制对这个对象的访问）倾向于给一个类做代理，而且在编译的时候就已经确定这个类 ，  装饰者模式（给一个类的方法进行拓展，编译时候不能确定拓展的类）
		Proxy  p = new Proxy();
		p.method();
		
		//08.外观模式(为子系统中的各类（或结构与方法）提供一个简明一致的界面，隐藏子系统的复杂性，使子系统更加容易使用。
		Computer com = new Computer();
		com.start();
		
		
		
		
	}
}
//******************************************↓↓↓↓↓↓↓↓工厂模式↓↓↓↓↓↓↓↓↓↓*********************************************************************
interface  IMobile {
	public void showMessage();
}

class AndroidPhone implements IMobile{

	@Override
	public void showMessage() {
		System.err.println("this is  android mobile...");
	}
	
}
class IosPhone implements IMobile{

	@Override
	public void showMessage() {
		System.err.println("this is  IOS mobile...");
	}
	
	
}

//简单工厂
class SimpleFactory{
	public static IMobile procdure(String type){
		if("android".equals(type)){
			return new AndroidPhone();
		}else if("ios".equals(type)){
			return new IosPhone();
		}else{
			System.err.println("please write correct type ..");
		}
		return null;
	}
	
}

//多个工厂模式
class ManyFactory{
	public static IMobile procdureAndroidPhone(){
		return new AndroidPhone();
	}
	
	public static  IMobile procdureIosPhone(){
		return new IosPhone();
	}
}

//抽象工厂
interface AbstractFactroy{
	public IMobile procdure();
} 

class  AndroidFactory implements AbstractFactroy{

	@Override
	public IMobile procdure() {
		return new AndroidPhone();
	}
	
}

class IosFactory implements AbstractFactroy{
	@Override
	public IMobile procdure() {
		return new IosPhone();
	}
}
//**********************************************单例模式*****************************************************
//饱汉模式
class HungrySington{
	private static HungrySington  instance =  new HungrySington();
	
	//私有化构造器
	private HungrySington() {
	}
	
	public static HungrySington getInstance(){
		return  instance;
	}
	
}

//饥汉模式
class FullSington{
	private static  FullSington instance;
	
	private FullSington() {
	}
	
	//线程不安全
	public static FullSington getInstance1(){
		if(instance == null){
			instance  =  new FullSington();
		}
		return instance;
	}
	
	//线程安全，但是锁了整个方法，我就第一次需要加锁，以后都不需要使用了，所以应该使用同步代码块
	public synchronized static FullSington getInstance2(){
		if(instance == null){
			instance  =  new FullSington();
		}
		return instance;
	}
	
	//一样不是线程安全的，因为java内存模型问题导致的，不过 你给 instance属性 加上volatile就完美了 
	public static FullSington getInstance3(){
			if(instance == null){
				synchronized (FullSington.class) {
					if(instance ==null)
					instance  =  new FullSington();
				}
		}
		return instance;
	}
	//最好这样写，静态内部类，用到的时候初始化，而且不会出现线程安全问题
	public static FullSington getInstance4(){
		return innerClass.instance;		
	}
	
	static class innerClass{
		private static final FullSington instance = new FullSington(); 
	}
	
	
}


//**********************************************适配器模式*****************************************************
//source
class People {
	public void  sayChinese(){
		System.err.println("speak chinese..");
	}
	
	public void  sayEnglish(){
		System.err.println("speak english..");
	}
}

interface ITarget{
	public void sayChinese();
	public void sayEnglish();
	public void sayFrench();
}

//类适配器
class ClassAdapter extends People implements ITarget{
	@Override
	public void sayFrench() {
		System.err.println("speak french..");
	}
}

class ObjectAdapter implements ITarget{
	private People  people ; 
	public ObjectAdapter() {
	}
	
	public ObjectAdapter(People p) {
		this.people = p;
	}
	
	@Override
	public void sayChinese() {
		people.sayChinese();
	}

	@Override
	public void sayEnglish() {
		people.sayEnglish();
	}

	@Override
	public void sayFrench() {
		System.err.println("speak french..");
	}
	
}

//**********************************************装饰模式*****************************************************
class DecorateSource implements IDecorate{
	
	public  void print(){
		System.err.println("123456");
	}
}

interface IDecorate{
	public  void print();
}

class Decorater implements IDecorate{

	private DecorateSource source ;
	
	public Decorater(DecorateSource s) {
		this.source = s;
	}
	@Override
	public void print() {
		System.err.println("= = =  = = = = = = = ");
		source.print();
		System.err.println("= = =  = = = = = = = ");
	}
}

//**********************************************代理模式*****************************************************
interface Sourceable{
	
	public void  method();
	
}

class Source implements Sourceable{

	@Override
	public void method() {
		System.err.println("this is oringnal method...");
	}
}

class Proxy implements Sourceable{

	private Source  source;
	
	public Proxy() {
		this.source = new Source();
	}
	@Override
	public void method() {
		System.err.println("before method....");
		source.method();
		System.err.println("after method....");
	}
}
//**********************************************外观模式*****************************************************

class CPU{
	
	public void cpuStart(){
		System.err.println("cpu startup");
	};
	
	public void cpuClose(){
		System.err.println("cpu shutdown");
	};
}

class Memory{
	
	public void memStart(){
		System.err.println("Memory startup");
	};
	
	public void memClose(){
		System.err.println("Memory shutdown");
	};
}

class Disk{
	
	public void diskStart(){
		System.err.println("Disk startup");
	};
	
	public void diskClose(){
		System.err.println("Disk shutdown");
	};
}

class Computer{
	private CPU cpu;
	private Memory mem;
	private Disk disk;
	
	public Computer() {
		this.cpu = new CPU();
		this.mem = new Memory();
		this.disk = new Disk();
	}
	
	public void start(){
		
		cpu.cpuStart();
		mem.memStart();
		disk.diskStart();
	};
	
	public void close(){
		cpu.cpuClose();
		mem.memClose();
		disk.diskClose();
	};
}

