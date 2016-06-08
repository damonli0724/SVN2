package com.saltedfish.activeMQ;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.saltedfish.activeMQ.queue.QueueSender;
import com.saltedfish.activeMQ.topic.TopicSender;


public class Test {
	
	public static void main(String[] args) {
		ApplicationContext ctx =  new ClassPathXmlApplicationContext("spring-root.xml");
		
		QueueSender   queueSender = (QueueSender) ctx.getBean("queueSender");
      	queueSender.send("queueName","Hello activeMq queue...");    
			 
//		TopicSender  topicSender =(TopicSender) ctx.getBean("topicSender");
//		topicSender.send("topicName","Hello activeMq topic...");
		
		
	}

}
