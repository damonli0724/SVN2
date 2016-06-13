package com.saltedfish.activeMQ.queue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @ClassName: QueueMessageListener 
 * @Description: 消息队列监听器
 * @author: lkd
 * @date: 2016年6月8日 下午12:49:55
 */
public class QueueMessageListener implements MessageListener {

	/**
	 * 测试事务，想要达到的效果，消费者，代码中出现问题，消息重发
	 */
	@Override
	public void onMessage(Message message) {
		System.err.println("开始消费消息.....");
		TextMessage textMessage = (TextMessage) message;
			
			try {
				System.err.println("[点对点模式]Queue监听者1收到信息,内容为:"+textMessage.getText());
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
