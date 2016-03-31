package com.saltedfish.controller.websocket2;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

@Component
public class WebSocketHandlerImpl implements WebSocketHandler{
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	//用来放每个用户对应的WebSocketSession
	private static final Map<Integer, WebSocketSession> WSessionPool ;
	
	static {
		WSessionPool = new HashMap<Integer, WebSocketSession>();	
	}
	
	/**
	 * 创建连接
	 * 
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		//1.在WebSocketSession中获取userId
		Integer wsUserId =  Integer.valueOf(session.getAttributes().get("wsUserId").toString());  
		//2.如果在session池里面没有这个userId，则放入
		if (WSessionPool.get(wsUserId)==null) 
			WSessionPool.put(wsUserId, session);
		
		logger.debug("WS-->用户Id为{}的用户已建立连接",wsUserId);
	}

	/**
	 * 消息处理，在客户端通过Websocket API发送的消息会经过这里，然后进行相应的处理
	 */
	@Override
	public void handleMessage(WebSocketSession session,
			WebSocketMessage<?> message) throws Exception {
		logger.debug("发送消息...");
	}

	
	/**
	 * 处理出错
	 */
	@Override
	public void handleTransportError(WebSocketSession session,
			Throwable exception) throws Exception {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 连接关闭
	 * 
	 */
	@Override
	public void afterConnectionClosed(WebSocketSession session,
			CloseStatus closeStatus) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}
	
	
	
}

