package com.saltedfish.service.dwr;


import java.util.Collection;
import java.util.LinkedList;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.proxy.dwr.Util;


/**
 * 用于服务器向页面发送群体消息
 * @author ethen
 */
public class Test2 {
	//消息列表
	private LinkedList messages = new LinkedList();
	
	/**
	 * 添加消息并发送到前台
	 * @param msg : 要发送的消息
	 */
	@SuppressWarnings("all")
	public void sendMessageAuto(String msg){
		if(null != msg && msg.length() > 0){
			messages.addFirst(msg);
			while(messages.size() > 10){
				messages.removeLast();
			}
		}
		//获取dwr的上下文
		WebContext wctx = WebContextFactory.get();
		//获取当前页面的url(请求路径)：要推送到页面的地址
		String currentPage = wctx.getCurrentPage();
		
		Util utilThis = new Util(wctx.getScriptSession());
		utilThis.setValue("text", "请输入信息");
		
		//获得所有已经打开此页面的会话(session)
		Collection sessions = wctx.getScriptSessionsByPage(currentPage);
		Util utilAll = new Util(sessions);
		
		//如果不删除chatlog的内容，那么表示以前的聊天内容会一直存在
		//utilAll.removeAllOptions("chatlog");
		String[] msgs = new String[messages.size()];
		msgs = (String[]) messages.toArray(msgs);
		utilAll.addOptions("chatlog", msgs);
	}
	
	/**
	 * 也不知道这种做法到底是不是真正的群聊效果实现，
	 * 只知道，这种做法可以实现在多个浏览器客户端的同一页面上即时显示用户发送的消息
	 */
}
