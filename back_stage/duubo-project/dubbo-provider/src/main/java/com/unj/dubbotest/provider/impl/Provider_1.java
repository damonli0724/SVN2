package com.unj.dubbotest.provider.impl;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Provider_1 {

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "applicationContext.xml" });
		context.start();
		System.out.println("Provider_1 is started.");
		System.in.read();
	}
}