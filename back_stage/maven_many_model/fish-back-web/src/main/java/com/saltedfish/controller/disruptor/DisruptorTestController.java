package com.saltedfish.controller.disruptor;

import java.nio.ByteBuffer;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.saltedfish.controller.base.BaseController;
import com.saltedfish.controller.constants.Url;
import com.saltedfish.controller.constants.View;
import com.saltedfish.entity.security.SysUsers;
import com.saltedfish.mapper.security.UserMapper;
import com.saltedfish.service.disruptor.LongEvent;
import com.saltedfish.service.disruptor.LongEventFactory;
import com.saltedfish.service.disruptor.LongEventHandler;
import com.saltedfish.service.disruptor.LongEventProducer;


@Controller
public class DisruptorTestController extends BaseController {
	@Autowired
	private UserMapper  userMapper;
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value = Url.DISCUPTOR_TEST, method = RequestMethod.GET)
	@ResponseBody
	public void turnToWelcomPage(HttpServletRequest request,ModelMap map) {
		  // Executor that will be used to construct new threads for consumers 
        Executor executor = Executors.newCachedThreadPool();
        // The factory for the event 
        LongEventFactory factory = new LongEventFactory();
        // Specify the size of the ring buffer, must be power of 2.
        int bufferSize = 1024;
        // Construct the Disruptor 
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory, bufferSize, executor);
        // Connect the handler 
        disruptor.handleEventsWith(new LongEventHandler(userMapper));
        // Start the Disruptor, starts all threads running 
        disruptor.start();
        // Get the ring buffer from the Disruptor to be used for publishing. 
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer(); 
 
        LongEventProducer producer = new LongEventProducer(ringBuffer); 
 
        
        
        ByteBuffer bb = ByteBuffer.allocate(8);
        
            bb.putLong(0, 1); 
            producer.onData(bb); 
		
	}
	
	
	@RequestMapping(value = Url.DISCUPTOR_TEST2, method = RequestMethod.GET)
	@ResponseBody
	public void turnToWelcomPage2(HttpServletRequest request,ModelMap map) {
		
        
//            bb.putLong(0, 1); 
//            producer.onData(bb); 
    	Date  date = new Date();
    	System.out.println("---------------处理信息"+date.getMinutes()+"->"+date.getSeconds()+"->index");
    	SysUsers u = new SysUsers();
    	u.setEmail("fdfdff");
    	u.setUsername("fdfdsf");
    	u.setDepName("fd");
    	u.setDescription("fefefe");
    	userMapper.addAdminUser(u);
	}
	

	
	
		
}
