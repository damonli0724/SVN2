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
	public void sendMessageAuto(String userid, String message){  
        final String userId = userid;   
        final String autoMessage = message;  
        //1.ScriptSessionFilter  ScriptSession过滤器 //可根据需求指定你要过滤的东西
        ScriptSessionFilter    filter  = new ScriptSessionFilter() {
			@Override //这里根据 根据用户的Id 执行指定发送消息
			public boolean match(ScriptSession session) {
				
				
				
				logger.debug("----->发送信息:接收者Id为{},当前用户Id为{}", userId,session.getAttribute("userId"));
			     if (session.getAttribute("userId") == null){
			    	 return false;
			     }else{
                     return (session.getAttribute("userId")).equals(userId);
			     }
			}
		};
		//2.任务
		Runnable  task  = new Runnable() {
			 private ScriptBuffer script = new ScriptBuffer();  
			@Override
			public void run() {
				logger.debug("----->匹配成功，开始回调showMessage函数..");
                script.appendCall("showMessage", autoMessage);  
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
