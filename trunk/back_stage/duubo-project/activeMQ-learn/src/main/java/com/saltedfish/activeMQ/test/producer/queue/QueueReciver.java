package com.saltedfish.activeMQ.test.producer.queue;

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
	private JmsTemplate jmsTemplate;
	
	
	public String getMessage(String  desName){
		String res = "";
		TextMessage  resVal = (TextMessage) jmsTemplate.receive(desName);
		try {
			res = resVal.getText();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	
	
}
