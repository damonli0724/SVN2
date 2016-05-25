package com.saltedfish.service.dwr;

import javax.servlet.ServletException;

import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContextFactory;

import com.saltedfish.service.utils.DwrScriptSessionManagerUtil;
/**
 * dwr 页面加载时候 初始化工具类，设置信息
 * @author 
 *
 */
public class MessageLoad {
	/**
	 * 监听页面初始化.
	 * 1.获取当前的ScriptSession
	 * 2.当前ScriptSession 添加属性 当前的userId
	 * 3.初始化DwrScriptSessionManagerUtil 管理类工具,调用初始化信息
	 * @param userId
	 */
	  public void onPageLoad(String userId) {  
		  
	      /* ScriptSession scriptSession = WebContextFactory.get().getScriptSession();  
	  
	       scriptSession.setAttribute(userId, userId);  */
	       DwrScriptSessionManagerUtil dwrScriptSessionManagerUtil = new DwrScriptSessionManagerUtil();  
	       try {  
	              dwrScriptSessionManagerUtil.init();  
	       } catch (ServletException e) {  
	              e.printStackTrace();  
	  
	       }  
	  
	}  
}
