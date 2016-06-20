/*******************************************************************************
 * Project   : auto-common
 * Class Name: com.yyq.auto.portal.common.util.JPushUtils
 * Created By: mjy 
 * Created on: 2015年10月8日 下午12:41:56
 * Copyright © 2013-2014 YYQ All rights reserved.
 ******************************************************************************/
package com.saltedfish.utils;


import org.apache.log4j.Logger;

import cn.jpush.api.JPushClient;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;


/**
 * 1.可以根据tag，alias，registrationId 对不同平台的用户发送信息
 * 2.setAudience(Audience.registrationId(xx))这里设置别名，注册Id，标签
 * 3..setApnsProduction(false) 当放入生产的时候,改成true
 * @ClassName: JPushUtils 
 * @Description: 极光推送工具类
 * @author: lkd
 * @date: 2016年6月17日 下午2:16:02
 */
public class JPushUtils {
	private static final String appKey = "43fd4d2ad1e2c2562aa1ac72";
	private static final String masterSecret = "03568170972bd082228dd08b";
	public static final String REGISTRATION_ID = "101d85590944c0b4593";
	public static final String TAG = "";
	public static final String HOST_NAME = "https://api.jpush.cn";

	protected  final static Logger logger = Logger.getLogger(JPushUtils.class);

	/**
	 * @Title: getJpushClient 
	 * @Description: 获得jpushClient 核心对象
	 * @return c
	 * @return: JPushClient
	 */
	public static JPushClient getJpushClient() {
		JPushClient jpushClient = new JPushClient(masterSecret, appKey);
		return jpushClient;
	}

	/**
	 * 
	 * @Title: sendMessageToAndroid 
	 * @Description: 根据android 用户accountId指定发送消息
	 * @param content
	 * @param title
	 * @param accountId
	 * @throws Exception
	 * @return: void
	 */
	public static void sendMessageToAndroid(String content, String title, Integer accountId) throws Exception {
		PushPayload payLoad = PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.alias(String.valueOf(accountId)))
				.setNotification(Notification.android(content, title, null)).build();
		JPushUtils.getJpushClient().sendPush(payLoad);
	}

	/**
	 * @Title: sendMessageToAll 
	 * @Description: 给所有平台 所有用户 发通知
	 * @param content
	 * @throws Exception
	 * @return: void
	 */
	public static void sendMessageToAll(String content) throws Exception {
		PushPayload payLoad = PushPayload.alertAll(content);
		JPushUtils.getJpushClient().sendPush(payLoad);
	}

	/**
	 * 
	 * @Title: sendMessageToALL 
	 * @Description: 根据注册Id发送到所有平台
	 * @param title
	 * @param content
	 * @param id
	 * @return: void
	 */
	public static void sendMessageToALL(String title, String content, String id) {
		try {
			PushPayload payLoad =	PushPayload.newBuilder()
            .setPlatform(Platform.all())
            .setAudience(Audience.registrationId(id))
            .setNotification(Notification.newBuilder()
            		.addPlatformNotification(AndroidNotification.newBuilder().setAlert(content).setTitle(title).build())
                    .addPlatformNotification(IosNotification.newBuilder()
                            .setAlert(content)
                            .setBadge(+1)
                            .setSound("happy.caf") 
                            .build())
                    .build())
             .setOptions(Options.newBuilder()
                     .setApnsProduction(false)
                     .build())
             .build();
			
			JPushUtils.getJpushClient().sendPush(payLoad);
		} catch (Exception e) {
			logger.error("=============极光推送异常=============:" + e.getMessage());
		}
	}
	
	

	/**
	 * @Title: sendMessageToAndroid 
	 * @Description: 根据别名发送到android平台
	 * @param title
	 * @param content
	 * @param id
	 * @return: void
	 */
	public static void sendMessageToAndroid(String title, String content, String id) {
		try {
			PushPayload payLoad =	PushPayload.newBuilder()
            .setPlatform(Platform.all())
            .setAudience(Audience.registrationId(id))
            .setNotification(Notification.newBuilder()
                    .addPlatformNotification(AndroidNotification.newBuilder()
                            .setAlert(content)
                            .build())
                    .build())
             .setOptions(Options.newBuilder()
                     .setApnsProduction(false)
                     .build())
             .build();
			
			JPushUtils.getJpushClient().sendPush(payLoad);
		} catch (Exception e) {
			logger.error("=============极光推送异常=============:" + e.getMessage());
		}
	}
	
	public static void main(String[] args) throws Exception {
		// JPushUtils.sendMessageToAndroidAndIos("标题-----------", "内容------------", 297);
		// JPushUtils.sendMessageToIos("标题--(个人推送)", "内容--(xxxx)", 304);
		// JPushUtils.sendMessageToAll("再来一遍？");
			
			//JPushUtils.sendMessageToALL("title", "fdsfsdfdsfdsfsdfdsf", "101d85590944c0b4593");
			JPushUtils.sendMessageToALL("标题", "内容", "101d85590944c0b4593");
			
	}
}
