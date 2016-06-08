package com.saltedfish.activeMQ.topic;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

/**
 * 
 * @ClassName: TopicMessageListener2 
 * @Description: 订阅监听者
 * @author: lkd
 * @date: 2016年6月8日 下午1:43:43
 */
public class TopicMessageListener2 implements MessageListener {

	@Override
	public void onMessage(Message message) {
		 TextMessage textMessage = (TextMessage) message;
		try {
			System.err.println("[订阅模式]Topic监听者2收到信息,内容为:"+textMessage.getText());
		} catch (JMSException e) { 
			e.printStackTrace(); 
		} 
	}
}
