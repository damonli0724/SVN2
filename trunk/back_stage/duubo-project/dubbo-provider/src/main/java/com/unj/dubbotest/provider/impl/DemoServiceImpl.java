package com.unj.dubbotest.provider.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.unj.dubbotest.provider.DemoService;
import com.unj.dubbotest.provider.User;

public class DemoServiceImpl implements DemoService {

	public String sayHello(String name) {
		int size = new Random().nextInt(100000);
		for(int i = 0; i < 100000 + size;i++);
		return "Hello " + name + " from provider_1";
	}

	public List<User> getUsers() {
		List<User> list = new ArrayList<User>();
		User u1 = new User();
		u1.setName("jack_1");
		u1.setAge(20);
		u1.setSex("m");

		User u2 = new User();
		u2.setName("tom_1");
		u2.setAge(21);
		u2.setSex("m");

		User u3 = new User();
		u3.setName("rose_1");
		u3.setAge(19);
		u3.setSex("w");

		list.add(u1);
		list.add(u2);
		list.add(u3);
		return list;
	}
}
