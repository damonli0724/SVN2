package com.saltedfish.service.webservice;

import java.lang.reflect.Method;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class WebServiceTest {

	public static void main(String[] args) throws Exception {
			//方式1
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

		factory.setServiceClass(IWebService.class);

		factory.setAddress("http://localhost:9088/fish-back-web/webservice/user?wsdl");

		IWebService client = (IWebService) factory.create();

		String response = client.sayHello("Hai,jiangqiao");
		
		
		
		

	}

}
