package com.saltedfish.activeMQ.queue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

/**
 * 消息监听者
 * @author lkd
 *
 */
public class QueueMessageListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		 TextMessage textMessage = (TextMessage) message;
		try {
			System.err.println("[点对点模式]Queue监听者1收到信息,内容为:"+textMessage.getText());
		} catch (JMSException e) { 
			e.printStackTrace(); 
		} 
	}
}
