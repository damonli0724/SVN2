package com.saltedfish.controller.websocket.config;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Socket处理器
 * @author lkd
 */
//@Component
public class CustomWebSocketHandler implements WebSocketHandler {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	public static final int  All = 0; //所有人的uid为 0
	public static final Map<String, WebSocketSession> userSocketSessionMap;
	public static final String dateFormate="yyyy-MM-dd HH:mm:ss";
	

	static {
		userSocketSessionMap = new HashMap<String, WebSocketSession>();
	}

	/**
	 * 建立连接后
	 * 建立连接，获取uid（用户的Id）,然后再session里面 key ->userId  value ->WebSocketSession
	 */
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		
		String uid = (String) session.getAttributes().get("uid"); 
		logger.debug("WebSocket:创建连接，uid为{}",uid);
		if (userSocketSessionMap.get(uid) == null) {
			userSocketSessionMap.put(uid, session);
			//提示页面 有用户上线了
			noticeUserOnLine();
		}
	}

	/**
	 * 消息处理，在客户端通过Websocket API发送的消息会经过这里，然后进行相应的处理
	 */
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
			if(message.getPayloadLength()==0)return;
			Message msg=new Gson().fromJson(message.getPayload().toString(),Message.class);
			msg.setDate(new Date());
			logger.debug("接收人的Id为{},内容为{}",msg.getReciveUserId(),msg.getContent());
			
			
			//发送给所有人
			if (msg.getReciveUserId()==All) {
				broadcast(new TextMessage(new GsonBuilder().setDateFormat(dateFormate).create().toJson(msg)));
				return;
			}
			
			sendMessageToUser(String.valueOf(msg.getReciveUserId()), new TextMessage(new GsonBuilder().setDateFormat(dateFormate).create().toJson(msg)));
	}

	/**
	 * 消息传输错误处理
	 */
	public void handleTransportError(WebSocketSession session,
			Throwable exception) throws Exception {
		if (session.isOpen()) {
			session.close();
		}
		Iterator<Entry<String, WebSocketSession>> it = userSocketSessionMap
				.entrySet().iterator();
		// 移除Socket会话
		while (it.hasNext()) {
			Entry<String, WebSocketSession> entry = it.next();
			if (entry.getValue().getId().equals(session.getId())) {
				userSocketSessionMap.remove(entry.getKey());
				System.out.println("Socket会话已经移除:用户ID" + entry.getKey());
				break;
			}
		}
	}

	/**
	 * 关闭连接后
	 */
	public void afterConnectionClosed(WebSocketSession session,
			CloseStatus closeStatus) throws Exception {
		System.out.println("Websocket:" + session.getId() + "已经关闭");
		Iterator<Entry<String, WebSocketSession>> it = userSocketSessionMap
				.entrySet().iterator();
		// 移除Socket会话
		while (it.hasNext()) {
			Entry<String, WebSocketSession> entry = it.next();
			if (entry.getValue().getId().equals(session.getId())) {
				userSocketSessionMap.remove(entry.getKey());
				System.out.println("Socket会话已经移除:用户ID" + entry.getKey());
				break;
			}
		}
	}

	public boolean supportsPartialMessages() {
		return false;
	}

	/**
	 * 给所有在线用户发送消息
	 * 
	 * @param message
	 * @throws IOException
	 */
	public void broadcast(final TextMessage message) throws IOException {
		Iterator<Entry<String, WebSocketSession>> it = userSocketSessionMap
				.entrySet().iterator();
		// 多线程群发
		while (it.hasNext()) {

			final Entry<String, WebSocketSession> entry = it.next();

			if (entry.getValue().isOpen()) {
				// entry.getValue().sendMessage(message);
				new Thread(new Runnable() {

					public void run() {
						try {
							if (entry.getValue().isOpen()) {
								entry.getValue().sendMessage(message);
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

				}).start();
			}

		}
	}

	/**
	 * 给某个用户发送消息
	 * 
	 * @param userName
	 * @param message
	 * @throws IOException
	 */
	public void sendMessageToUser(String uid, TextMessage message)
			throws IOException {
		WebSocketSession session = userSocketSessionMap.get(uid);
		if (session != null && session.isOpen()) {
			session.sendMessage(message);
		}
	}
	
	/**
	 * 提示用户上线
	 * @throws IOException
	 */
	private void noticeUserOnLine() throws IOException {
		Message  m = new Message();
		m.setContent("有用户上线了!");
		m.setDate(new Date());
		m.setReciveUserId(All);
		m.setSendUserId(-1);
		m.setSendUserName("系统消息");
		broadcast(new TextMessage(new GsonBuilder().setDateFormat(dateFormate).create().toJson(m)));
	}


	
	
}
