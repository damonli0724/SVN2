package com.saltedfish.service.dwr;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;

import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.event.ScriptSessionEvent;
import org.directwebremoting.event.ScriptSessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DWRScriptSessionListener implements ScriptSessionListener{
	private static final Logger  logger = LoggerFactory.getLogger(DWRScriptSessionListener.class);
	 //维护一个Map key为session的Id， value为ScriptSession对象
    public static final Map<String, ScriptSession> scriptSessionMap = new ConcurrentHashMap<String, ScriptSession>();
	
	@Override
	public void sessionCreated(ScriptSessionEvent ev) {
		 //获取httpsession
		 WebContext webContext = WebContextFactory. get();
         HttpSession session = webContext.getSession();
         //获取创建的seciptSession
         ScriptSession scriptSession = ev.getSession();
         
         //添加scriptSession
         scriptSessionMap.put(session.getId(), scriptSession);    
		
         logger.debug("<DWR>:httpsession ID is{},scriptSession ID is{} has been puted into scriptSessionMap",session.getId(),scriptSession.getId());
         
	}

	@Override
	public void sessionDestroyed(ScriptSessionEvent ev) {
		  WebContext webContext = WebContextFactory. get();
          HttpSession session = webContext.getSession();
          logger.debug("<DWR>:httpsession ID is{},scriptSession ID is{} has been remove from scriptSessionMap",session.getId(),ev.getSession().getId());
          ScriptSession scriptSession = scriptSessionMap.remove(session.getId());  //根据httpsession ID 移除scriptSession
	}
	
	
	/**
	 * 获取所有ScriptSession
	 * @return Collection<ScriptSession>
	 */
    public static Collection<ScriptSession> getScriptSessions(){
           return scriptSessionMap.values();
    }

}
