package com.saltedfish.activeMQ.topic;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 消息监听者
 * @author lkd
 *
 */
public class TopicMessageListener2 implements MessageListener {

	@Override
	public void onMessage(Message message) {
		 TextMessage textMessage = (TextMessage) message;
		try {
			System.err.println("==================");
			System.err.println("Topic....2222222222222收到信息。。。。。内容为-->"+textMessage.getText());
		} catch (JMSException e) { 
			e.printStackTrace(); 
		} 
	}
}
