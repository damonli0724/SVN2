package com.saltedfish.activeMQ.queue;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class QueueReciver {
	@Autowired
	private JmsTemplate queueJmsTemplate;
	/**
	 *  根据队列名称获取信息
	 * @param queueName 队列名称
	 * @return
	 */
	public String getMessage(String queueName) {
		String res = "";
		TextMessage resVal = (TextMessage) queueJmsTemplate.receive(queueName);
		try {
			res = resVal.getText();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		return res;
	}
}
