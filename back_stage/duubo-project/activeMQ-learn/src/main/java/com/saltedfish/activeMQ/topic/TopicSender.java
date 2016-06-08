package com.saltedfish.activeMQ.topic;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

/**
 * 
 * @ClassName: TopicSender 
 * @Description: 订阅 发送者
 * @author: lkd
 * @date: 2016年6月8日 下午12:48:35
 */
@Service
public class TopicSender {
	
	@Autowired
	private JmsTemplate topicJmsTemplate;
	

	/**
	 * 
	 * @Title: 发送一条消息到指定的队列（目标） 
	 * @Description: 根据队列名称，发送信息到指定地点
	 * @param topicName  队列名称
	 * @param message 消息内容
	 * @return: void
	 */
	public void send(final String topicName,final String message){
		topicJmsTemplate.send(topicName,new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});
	}

	
	
}
