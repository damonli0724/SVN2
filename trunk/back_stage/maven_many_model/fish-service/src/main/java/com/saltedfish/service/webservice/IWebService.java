package com.saltedfish.service.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * 创建服务类接口
 * @author lkd
 */
@WebService
public interface IWebService {
	/**
	 *注意：“@WebService”标记表示该接口是一个WebService服务；“@WebMethod”表示表示以下方法为WebService服务中的方法；
	 *@WebParam(name="username")”表示方法中的参数，username属性限制了参数的名称，若没有指定该属性，参数将被重命名。 
	 */
	@WebMethod
	public String sayHello(@WebParam(name="username")String username);
	public void setUser(String username);
	
}
