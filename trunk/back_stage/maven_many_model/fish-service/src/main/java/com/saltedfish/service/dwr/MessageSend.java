package com.saltedfish.service.dwr;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessionFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * dwr 推送消息发送
 * @author lkd
 *
 */
public class MessageSend {
	private static final Logger  logger = LoggerFactory.getLogger(MessageSend.class);
	//根据UserId 发送message
	public void sendMessageByUserId(final String userId , final String message){  
        ScriptSessionFilter    filter  = new ScriptSessionFilter() {
        	//这里根据 根据用户的Id 执行指定发送消息 //这里会循环 所有的scriptSession,session的userId 和当前的userId作比较
        	//匹配  对当前页面，回调函数
			@Override 
			public boolean match(ScriptSession session) {
				Object sessionUserId = session.getAttribute("userId");
				logger.debug("----->发送信息:接收者Id为{},当前用户Id为{}", userId,sessionUserId);
			     if ( sessionUserId== null){
			    	 return false;
			     }else{
                     return sessionUserId.equals(userId);
			     }
			}
		};
		//2.任务（回调函数）
		Runnable  task  = new Runnable() {
		  private ScriptBuffer script = new ScriptBuffer();  
			@Override
			public void run() { 
				logger.debug("----->匹配成功，开始回调showMessage函数..");
                script.appendCall("showMessage", message);  
                Collection<ScriptSession> sessions = Browser.getTargetSessions();  
                for (ScriptSession scriptSession : sessions){
                    scriptSession.addScript(script);  
                }  
			}
		};
         //3.发送
		 Browser.withAllSessionsFiltered(filter, task);
    }  
}
