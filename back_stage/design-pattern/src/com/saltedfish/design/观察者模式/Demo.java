package com.saltedfish.design.观察者模式;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @ClassName: Demo 
 * @Description: 珠宝商运送一批钻石，有黄金强盗准备抢劫，珠宝商雇佣了私人保镖，警察局也派人护送，于是当运输车上路的时候，强盗保镖警察都要观察运输车一举一动，
 * @author: Administrator
 * @date: 2017年4月25日 下午6:39:01
 */
public class Demo {
	public static void main(String[] args) {
		Car car = new Car();
		car.add(new Plice());
		car.add(new Thief());
		car.moving();
		
	}
}

//观察者 的动作方法，抽出来为接口
interface Watcher{
	public void doSomeThings();
}

//贼
class Thief implements Watcher{
	@Override
	public void doSomeThings() {
		System.err.println("运输车动了，准备行动..");
	}
}

class Plice implements Watcher{
	@Override
	public void doSomeThings() {
		System.err.println("运输车动了，准备护送..");
	}
}

//被观察者，运输车，抽出方法接口
interface watched{
	public void add(Watcher watcher);
	public void del(Watcher watcher);
	public void notifyAllWatchers();
}

//抽象类，被观察者，用于具体被观察物体，继承用的
abstract class SubjectWatched implements watched{
	private static final Vector<Watcher> watchers = new Vector<>();
	
	@Override
	public void add(Watcher watcher) {
		watchers.add(watcher);
	}
	
	@Override
	public void del(Watcher watcher) {
		watchers.remove(watcher);
	}
	
	@Override
	public void notifyAllWatchers() { 
		Enumeration<Watcher> enumo = watchers.elements();
		while (enumo.hasMoreElements()) {
			enumo.nextElement().doSomeThings();
		}
		
	}
	
	public abstract void moving();
	
}

class Car extends SubjectWatched{

	@Override
	public void moving() {
		System.err.println("动了...");
		notifyAllWatchers();
	}
	
}


