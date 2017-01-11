/**   
 * Copyright © 2017 公司名. All rights reserved.
 * 
 * @Title: Prototype.java 
 * @Prject: design-pattern
 * @Package: com.saltedfish.design.原型模式 
 * @Description: TODO
 * @author: lkd   
 * @date: 2017年1月11日 下午2:02:17 
 * @version: V1.0   
 */
package com.saltedfish.design.原型模式;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/** 
 * @ClassName: Prototype 
 * @Description:  原型模式
 * 一个原型类，只需要实现Cloneable接口，覆写clone方法 
 * 
 * 浅复制：将一个对象复制后，基本数据类型的变量都会重新创建，而引用类型，指向的还是原对象所指向的。
 * 深复制：将一个对象复制后，不论是基本数据类型还有引用类型，都是重新创建的。简单来说，就是深复制进行了完全彻底的复制，而浅复制不彻底。
 * 
 * @author: lkd
 * @date: 2017年1月11日 下午2:02:17  
 */
public class Prototype  implements Cloneable,Serializable{
	
	
	public String  name="xx";

	private static final long serialVersionUID = 1L; 
	
	
	/* 浅克隆：
	 * 比如一个对象 User ，  User a = new User();    User b =  a.clone();  
	 * a对象，和 b 对象是不相等的，但是 a对象和b对象都是指向用一个内存地址的。所以属性都是相同的 ， 其实就是调用 object父类的 clone方法，
	 */
	public Object clone() throws CloneNotSupportedException{ 
		Prototype prt = (Prototype) super.clone();
		return prt;
	}
	
	/* 深克隆:
	 */
	public  Object deepClone() throws IOException, ClassNotFoundException {
		   /* 写入当前对象的二进制流 */  
		   ByteArrayOutputStream bos = new ByteArrayOutputStream();  
		   ObjectOutputStream oos = new ObjectOutputStream(bos);  
		   oos.writeObject(this);
		   
		   /* 读出二进制流产生的新对象 */  
	        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());  
	        ObjectInputStream ois = new ObjectInputStream(bis);  
	        return ois.readObject();  
	}
	
	
	
	//test
	public static void main(String[] args) throws CloneNotSupportedException, ClassNotFoundException, IOException {
		Prototype  pto = new Prototype();
		Prototype pto2 = null;
		try {
			pto2 = (Prototype) pto.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		System.err.println(pto==pto2);
		System.err.println(pto.name+"---"+pto2.name);
		
		
		Prototype  pto3 = (Prototype) pto.deepClone() ;  
		
		System.err.println(pto==pto3);
		System.err.println(pto.name+"---"+pto3.name);
		
		
		
		
		
	}

}
