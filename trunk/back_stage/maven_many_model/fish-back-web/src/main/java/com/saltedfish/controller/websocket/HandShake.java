
package com.saltedfish.controller.websocket;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.saltedfish.dto.security.UserListDTO;


/**
 * Socket建立连接（握手）和断开
 * @author lkd
 */
public class HandShake implements HandshakeInterceptor {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
		//获取已登录的用户
		UserListDTO user =  (UserListDTO) ((ServletServerHttpRequest) request).getServletRequest().getSession(false).getAttribute("user");
		
		logger.debug("Websocket:用户Id:{},用户名{},建立连接",user.getUserId(),user.getName());
		
		if (request instanceof ServletServerHttpRequest) {
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
			HttpSession session = servletRequest.getServletRequest().getSession(false);
			// 标记用户    
			String uid = (String) session.getAttribute("uid");
			if(uid!=null){
				attributes.put("uid", uid);    
			}else{
				return false;
			}
		}
		return true;
	}

	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
//		UserListDTO user =  (UserListDTO) ((ServletServerHttpRequest) request).getServletRequest().getSession(false).getAttribute("user");
//		logger.debug("Websocket:用户Id:{},用户名{},已断开连接",user.getUserId(),user.getName());
	}

}
