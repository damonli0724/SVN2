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

/**
 * 
 * @ClassName: QueueSender 
 * @Description: 队列发送器
 * @author: lkd
 * @date: 2016年6月8日 下午12:50:11
 */
@Service 
public class QueueSender {
	
	//@Autowired 在spirng高的版本中，根据类型查找，如果有多个，则再根据名字，如果出现多个，或者没有，则异常
	@Autowired
	private JmsTemplate queueJmsTemplate;
	
	/**
	 * 
	 * @Title: 发送信息到指定队列 
	 * @Description: 
	 * @param queueName 队列名称
	 * @param message 消息内容
	 * @return: void
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
