package com.saltedfish.design.观察者模式;

import java.util.Enumeration;
import java.util.Vector;

/**
 * 
 * @ClassName: Client
 * @Description: 观察者模式
 * @author: lkd
 * @date: 2017年4月25日 下午2:31:08
 */
public class Client {

	public static void main(String[] args) {
		  
		  Subject sub = new MySubject();  
		  sub.add(new Observer1());  
          sub.add(new Observer2());  
          sub.oprate(); 
	}
}

// 观察接口
interface Observer {
	public void update();
}

// 观察者1
class Observer1 implements Observer {
	@Override
	public void update() {
		System.err.println("Observer1 暗中窥察...");
	}
}

// 观察者1
class Observer2 implements Observer {
	@Override
	public void update() {
		System.err.println("Observer2 暗中窥察...");
	}
}

// 主题接口
interface Subject {
	public void add(Observer ob);// 添加观察者

	public void del(Observer ob);// 删除观察者

	public void notifyObservers();// 唤醒观察者

	public void oprate();// 该类本身的操作
}

// 抽象主题类
abstract class AbstractSubject implements Subject {
	private static final Vector<Observer> observers = new Vector<Observer>();

	@Override
	public void add(Observer ob) {
		observers.add(ob);
	}

	@Override
	public void del(Observer ob) {
		observers.remove(ob);
	}

	@Override
	public void notifyObservers() {
		Enumeration<Observer> enumo = observers.elements();
		while (enumo.hasMoreElements()) {
			enumo.nextElement().update();
		}
	}

}

class MySubject  extends AbstractSubject{
	@Override
	public void oprate() {
		    System.out.println("update self!");  
		    notifyObservers();  
	}
}


