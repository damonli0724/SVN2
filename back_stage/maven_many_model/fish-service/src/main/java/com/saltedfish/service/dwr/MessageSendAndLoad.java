package com.saltedfish.service.dwr;

import java.util.Collection;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessionFilter;
import org.directwebremoting.WebContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageSendAndLoad {
	private static final Logger  logger = LoggerFactory.getLogger(DWRScriptSessionListener.class);
	/**
	 * 页面加载的时候，这是tag标签,后面根据tag标签进行发送信息
	 * @param tag
	 */
	public void onPageLoad(final String tag){
        //获取当前的ScriptSession
        ScriptSession scriptSession = WebContextFactory.get().getScriptSession();
        scriptSession.setAttribute( "tag", tag);
        logger.debug("给当前的scriptSession 设置属性tag 值为{}",tag);
        
	}
	
	
	
	/**
	 * 消息发送，根据特定的标签发送信息
	 * @param content
	 */
	public void send(final String content){
        //过滤器
       ScriptSessionFilter filter = new ScriptSessionFilter() {
            
             public boolean match(ScriptSession scriptSession) {
                 String tag = (String)scriptSession.getAttribute("tag" );
                  return "receiverTag" .equals(tag);
            }
       };

       //回掉函数
       Runnable run = new Runnable(){
             private ScriptBuffer script = new ScriptBuffer();
             public void run() {
                  //设置要调用的 js及参数
                  script.appendCall( "show", content);
                  //得到所有ScriptSession
                 Collection<ScriptSession> sessions = DWRScriptSessionListener.getScriptSessions();
                  //遍历每一个ScriptSession
                  for (ScriptSession scriptSession : sessions){
                       scriptSession.addScript( script);
                 }
            }
       };
        //执行推送
       Browser. withAllSessionsFiltered(filter, run);    //注意这里调用了有filter功能的方法
}
	
	

}
