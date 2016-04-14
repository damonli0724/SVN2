package com.saltedfish.service.disruptor;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lmax.disruptor.EventHandler;
import com.saltedfish.dto.UserDTO;
import com.saltedfish.entity.security.SysUsers;
import com.saltedfish.mapper.security.UserMapper;
/**定义事件处理的具体实现**/
public class LongEventHandler implements EventHandler<LongEvent>{
	private UserMapper  mapper ; 
	public LongEventHandler() {
	}
	public LongEventHandler(UserMapper mapper) {
		this.mapper = mapper;
	}
	
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch)
    {
    	Date  date = new Date();
    	System.out.println(event.getValue()+"---------------处理信息"+date.getMinutes()+"->"+date.getSeconds()+"->index");
    	SysUsers u = new SysUsers();
    	u.setEmail("fdfdff");
    	u.setUsername("fdfdsf");
    	u.setDepName("fd");
    	u.setDescription("fefefe");
    	mapper.addAdminUser(u);
    }
}
