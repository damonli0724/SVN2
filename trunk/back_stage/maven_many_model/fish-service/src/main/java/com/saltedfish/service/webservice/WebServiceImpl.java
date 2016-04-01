package com.saltedfish.service.webservice;

public class WebServiceImpl implements IWebService{

	@Override
	public String sayHello(String username) {
		// TODO Auto-generated method stub
		 return "Hello " + username;  
	}

	@Override
	public void setUser(String username) {
		// TODO Auto-generated method stub
		
	}

}
