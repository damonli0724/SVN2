package com.saltedfish.service.utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.directwebremoting.Container;
import org.directwebremoting.ServerContextFactory;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.event.ScriptSessionEvent;
import org.directwebremoting.event.ScriptSessionListener;
import org.directwebremoting.extend.ScriptSessionManager;
import org.directwebremoting.servlet.DwrServlet;

import com.saltedfish.entity.security.SysUsers;
  
public class DwrScriptSessionManagerUtil extends DwrServlet{  
  
    private static final long serialVersionUID = -7504612622407420071L;  
    
  
    public void init()throws ServletException {  
  
           Container container = ServerContextFactory.get().getContainer();  
           ScriptSessionManager manager = container.getBean(ScriptSessionManager.class);  
           
           //对当前的用户创建一个监听器
           ScriptSessionListener listener = new ScriptSessionListener() {  
                  public void sessionCreated(ScriptSessionEvent ev) {  
                	  
                	  	//获取 javax.servlet.http.HttpSession
                         HttpSession session = WebContextFactory.get().getSession();  
                         
                         //获取用户的UserId
                         String userId =((SysUsers) session.getAttribute("user")).getUserId()+"";  
                         
                         //ScriptSessionEvent 对这个userId添加相应事件
                         ev.getSession().setAttribute("userId", userId);  
                         
                         System.err.println("a ScriptSession is created! userId is "+userId); 
                  }  
                  public void sessionDestroyed(ScriptSessionEvent ev) {  
                         System.err.println("a ScriptSession is distroyed");  
                  }  
           };  
           manager.addScriptSessionListener(listener);  
    }  
} 