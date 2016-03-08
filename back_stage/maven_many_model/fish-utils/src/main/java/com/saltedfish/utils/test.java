package com.saltedfish.utils;

import java.util.Date;

public class test {
private Integer id ;
private String name;
private String assda;
private String xx;
private Date date;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getAssda() {
	return assda;
}
public void setAssda(String assda) {
	this.assda = assda;
}
public String getXx() {
	return xx;
}
public void setXx(String xx) {
	this.xx = xx;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}


@Override
	public boolean equals(Object obj) {
	if (obj==null)return false;
	 test t  =(test) obj;
	
	
		return  (this.getId()+this.getAssda()+this.getName()+this.getXx()).hashCode()==(t.getId()+t.getAssda()+t.getName()+t.getXx()).hashCode();
	}

public static void main(String[] args) {
	
	test t1 = new test();
	t1.setId(1);
	t1.setAssda("xx");
	t1.setDate(new Date());
	t1.setName("lkd");
	t1.setXx("tt");
	try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	test t2 = new test();
	t2.setId(1);
	t2.setAssda("xx");
	t2.setDate(new Date());
	t2.setName("lkd");
	t2.setXx("tt");
	
	
	int tStr1 = (t1.getAssda()+t1.getName()+t1.getXx()+t1.getXx()+t1.getDate()).hashCode();
	int tStr2 = (t2.getAssda()+t2.getName()+t2.getXx()+t2.getXx()+t2.getDate()).hashCode();
	System.err.println(tStr1);
	System.err.println(tStr2);
	
	System.err.println(tStr1==tStr2);
	
	System.err.println(t1.equals(null));
	
	System.err.println(t1.equals(t1));
	
}
}
