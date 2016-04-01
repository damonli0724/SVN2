package com.saltedfish.controller.websocket.config;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.saltedfish.entity.security.SysUsers;

public class HandshakeInterceptorImpl implements HandshakeInterceptor{
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 握手前
	 * 握手前 把HttpSession里面的userId放入到WebSocketSession里面的 属性（attributes）;
	 */
	@Override
	public boolean beforeHandshake(ServerHttpRequest request,
			ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		
		if (request instanceof ServletServerHttpRequest) { //如果是http请求
			
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
			HttpSession session = servletRequest.getServletRequest().getSession(false);
			Object  obj = session.getAttribute("user");
			
			if (obj!=null) {
				SysUsers user =  (SysUsers) obj;
				attributes.put("wsUserId", user.getUserId());
				logger.debug("WS-->握手,将userId为 {}传入WebSocketSession的属性中,用户名登录名为{}",user.getUserId(),user.getName());
			}else{
				return false;
			}
		}
		return true;
	}

	/**
	 * 握手后
	 */
	@Override
	public void afterHandshake(ServerHttpRequest request,
			ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception exception) {
	}

}
