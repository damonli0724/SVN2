package com.saltedfish.activeMQ.topic;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

/**
 * 
 * @ClassName: TopicMessageListener 
 * @Description: 订阅监听器
 * @author: lkd
 * @date: 2016年6月8日 下午1:43:22
 */
public class TopicMessageListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		 TextMessage textMessage = (TextMessage) message;
		try {
			System.err.println("[订阅模式]Topic监听者1收到信息,内容为:"+textMessage.getText());
		} catch (JMSException e) { 
			e.printStackTrace(); 
		} 
	}
}
