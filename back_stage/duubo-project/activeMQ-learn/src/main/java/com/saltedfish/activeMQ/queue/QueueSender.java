package com.saltedfish.activeMQ.queue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class QueueSender {
	
	/**
	 * 这里的queueJmsTemplate 已经指定了 queue的名字，所以用这个发送 直接发送到指定的地方
	 */
	@Autowired
	private JmsTemplate queueJmsTemplate;
	
	/**
	 * 发送一条消息到指定的队列（目标）
	 * @param queueName 队列名称
	 * @param message 消息内容
	 */
	public void send(final String queueName ,final String message){
		queueJmsTemplate.send(queueName,new MessageCreator() {
			@Override 
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});
	}
	
}
