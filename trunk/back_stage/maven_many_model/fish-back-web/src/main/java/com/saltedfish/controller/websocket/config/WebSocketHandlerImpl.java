package com.saltedfish.controller.websocket.config;

import java.io.IOException;
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

@Component
public class WebSocketHandlerImpl implements WebSocketHandler {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	// 用来放每个用户对应的WebSocketSession
	private static final Map<Integer, WebSocketSession> WSessionPool;
	//用来放已登录的用户
	

	private static final Integer allReciver;
	private static final Integer systemSend;
 
	static {
		WSessionPool = new HashMap<Integer, WebSocketSession>();
		allReciver = 0; // 发给所有人
		systemSend = -1; // 系统发送
	}

	/**
	 * 创建连接
	 * 
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		// 1.在WebSocketSession中获取userId
		Integer wsUserId = Integer.valueOf(session.getAttributes()
				.get("wsUserId").toString());
		// 2.如果在session池里面没有这个userId，则放入
		if (WSessionPool.get(wsUserId) == null)
			WSessionPool.put(wsUserId, session);

		
		logger.debug("WS-->用户Id为{}的用户已建立连接", wsUserId);
	}

	/**
	 * 消息处理，在客户端通过Websocket API发送的消息会经过这里，然后进行相应的处理
	 */
	@Override
	public void handleMessage(WebSocketSession session,
			WebSocketMessage<?> message) throws Exception {
		// 1.根据前台传送的 json 解析成message对象

		Message m = new Gson().fromJson(message.getPayload().toString(),
				Message.class);
				m.setDate(new Date());
		
		// 如果接收人的Id为 0（所有人）,则发送给所有人
		if (m.getReciveUserId() == allReciver) {
			sendToAllUser(m);
			return;
		}

		//
		sendToUser(m);

	}

	/**
	 * 处理出错
	 */
	@Override
	public void handleTransportError(WebSocketSession session,
			Throwable exception) throws Exception {
		if (session.isOpen()) {
			session.close();
		}
		Iterator<Entry<Integer, WebSocketSession>> it = WSessionPool.entrySet()
				.iterator();
		// 移除Socket会话
		while (it.hasNext()) {
			Entry<Integer, WebSocketSession> entry = it.next();
			if (entry.getValue().getId().equals(session.getId())) {
				WSessionPool.remove(entry.getKey());
				logger.debug("处理错误,Socket会话已经移除:用户ID", entry.getKey());
				break;
			}
		}

	}

	/**
	 * 连接关闭
	 * 
	 */
	@Override
	public void afterConnectionClosed(WebSocketSession session,
			CloseStatus closeStatus) throws Exception {
		Iterator<Entry<Integer, WebSocketSession>> it = WSessionPool.entrySet()
				.iterator();
		// 移除Socket会话
		while (it.hasNext()) {
			Entry<Integer, WebSocketSession> entry = it.next();
			if (entry.getValue().getId().equals(session.getId())) {
				WSessionPool.remove(entry.getKey());
				logger.debug("连接关闭,Socket会话已经移除:用户ID", entry.getKey());
				break;
			}
		}
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

	/**
	 * 发给所有用户
	 * 
	 * @throws IOException
	 */
	public void sendToAllUser(final Message m) {
		final Iterator<Entry<Integer, WebSocketSession>> it = WSessionPool
				.entrySet().iterator();

		while (it.hasNext()) {
			final Entry<Integer, WebSocketSession> en = it.next();
			if (en.getValue().isOpen()) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							en.getValue().sendMessage(
									new TextMessage(new Gson().toJson(m)
											.getBytes("utf-8")));
							logger.debug("群发信息:{}",m.getContent());
						} catch (IOException e) {
							logger.error("WS-->群发信息异常{}", e.getMessage());
							e.printStackTrace();
						}
					}
				}).start();
			}

		}
	}

	/**
	 * 发给指定Id用户
	 * 
	 * @param m
	 * @throws IOException
	 */
	public void sendToUser(Message m) throws IOException {
		Integer reciveUserId = m.getReciveUserId();
		WebSocketSession session = WSessionPool.get(reciveUserId);
		if (session != null) { // 如果池中有这个session，则发送
			session.sendMessage(new TextMessage(new Gson().toJson(m).getBytes("utf-8")));
			logger.debug(
					"发送人Id为{},发送消息{},至接收人Id为{}",
					new Object[] { m.getSendUserId(), m.getContent(),
							m.getReciveUserId() });
		}

	}

}
