package com.saltedfish.service.dwr;

import java.util.Collection;
import java.util.Iterator;

import org.directwebremoting.Container;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ServerContextFactory;
import org.directwebremoting.event.ScriptSessionEvent;
import org.directwebremoting.event.ScriptSessionListener;
import org.directwebremoting.extend.ScriptSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * dwr 页面加载时候 初始化工具类，设置信息
 * @author 
 *
 */
public class MessageLoad {
	private static final Logger logger  = LoggerFactory.getLogger(MessageLoad.class);
	
	/**
	 * 监听页面初始化.
	 * 1.获取当前的ScriptSession
	 * 2.当前ScriptSession 添加属性 当前的userId
	 * 3.初始化DwrScriptSessionManagerUtil 管理类工具,调用初始化信息
	 * @param userId   用户的ID
	 */
	  public void onPageLoad(final String userId) {  
		  //1.获取scriptSessionManager 
          Container container = ServerContextFactory.get().getContainer();  
          final ScriptSessionManager manager = container.getBean(ScriptSessionManager.class);  
          
          //2.对当前的用户创建一个监听器
          ScriptSessionListener listener = new ScriptSessionListener() {  
       	   @Override
       	   public void sessionCreated(ScriptSessionEvent ev) {  
                   //获取用户的UserId
                   //新建一个页面，如果调用这个方法，则就生成一个scriptSession对象
                   //给当前scriptSession 添加属性,userId, 后面会遍历所有的scriptSession，如果发现这个scriptSession
                   //里面有这个属性，则可以根据userId属性指定 发送信息，回调函数
       		   		ScriptSession session = ev.getSession();
       		   		
       		   		session.setAttribute("userId", userId);  
       		   		logger.debug("DWR--sessionScript{} has been created....session attribute 'userId' is{} ",session,userId);
            }  
       	  @Override
            public void sessionDestroyed(ScriptSessionEvent ev) {
 		   			logger.debug("DWR--sessionScript{} has been destoryed....session attribute 'userId' is{} ",ev.getSession(), ev.getSession().getAttribute("userId"));
            }  
          };
          //3.添加ScriptSessionListener到 manager
          manager.addScriptSessionListener(listener);  
	}  
}
