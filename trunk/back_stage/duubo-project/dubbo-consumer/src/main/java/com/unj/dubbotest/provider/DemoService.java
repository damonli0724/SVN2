package com.unj.dubbotest.provider;

import java.util.List;

public interface DemoService {

	String sayHello(String name);

	public List<User> getUsers();

}