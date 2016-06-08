package com.saltedfish.activeMQ.queue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

/**
 * 
 * @ClassName: QueueMessageListener 
 * @Description: 消息队列监听器
 * @author: lkd
 * @date: 2016年6月8日 下午12:49:55
 */
public class QueueMessageListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		 TextMessage textMessage = (TextMessage) message;
		try {
			
			int a = 1/0;
			System.err.println("[点对点模式]Queue监听者1收到信息,内容为:"+textMessage.getText());
			
		} catch (JMSException e) { 
			e.printStackTrace(); 
		} 
	}
}
