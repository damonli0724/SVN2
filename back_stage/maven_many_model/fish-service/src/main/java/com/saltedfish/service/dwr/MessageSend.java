package com.saltedfish.service.dwr;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessionFilter;
/**
 * dwr 推送消息发送
 * @author lkd
 *
 */
public class MessageSend {
	
	//根据UserId 发送message
	public void sendMessageAuto(String userid, String message){  
        final String userId = userid;   
        final String autoMessage = message;  
        //1.ScriptSessionFilter  ScriptSession过滤器 //可根据需求指定你要过滤的东西
        ScriptSessionFilter    filter  = new ScriptSessionFilter() {
			@Override //这里根据 根据用户的Id 执行指定发送消息
			public boolean match(ScriptSession session) {
				System.err.println("------------"+session.getAttribute("userId"));
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
				//script  添加回掉函数(js中的函数)
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
