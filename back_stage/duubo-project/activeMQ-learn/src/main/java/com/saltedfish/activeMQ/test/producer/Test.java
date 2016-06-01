package com.saltedfish.activeMQ.test.producer;

import javax.jms.Destination;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.saltedfish.activeMQ.test.producer.queue.QueueReciver;
import com.saltedfish.activeMQ.test.producer.queue.QueueSender;

public class Test {
	
	public static void main(String[] args) {
		
		ApplicationContext ctx =  new ClassPathXmlApplicationContext("spring-root.xml");
		QueueSender   queueSender = (QueueSender) ctx.getBean("queueSender");
		QueueReciver   queueReciver  = (QueueReciver) ctx.getBean("queueReciver");
		
		
		queueSender.send("xxxxxxx", "fewfwe");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.err.println(queueReciver.getMessage("xxxxxxx"));
		
	}

}
