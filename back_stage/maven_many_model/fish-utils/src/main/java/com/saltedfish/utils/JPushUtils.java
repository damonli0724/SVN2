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
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;


/**
 * 极光推送工具类
 * <P>TODO</P>
 * @author lkd
 */
public class JPushUtils {
	private static final String appKey = "1c18605f35cd334d5cec4df5";
	private static final String masterSecret = "73d466f60c7392003d660d17";
	public static final String REGISTRATION_ID = "101d85590944c0b4593";
	public static final String TAG = "";
	public static final String HOST_NAME = "https://api.jpush.cn";

	protected  final static Logger logger = Logger.getLogger(JPushUtils.class);

	/**
	 * 获得jpushClient 核心对象
	 * <p>TODO</p>
	 * @return
	 * @author lkd
	 */
	public static JPushClient getJpushClient() {
		JPushClient jpushClient = new JPushClient(masterSecret, appKey);
		return jpushClient;
	}

	/**
	 * 根据android 用户accountId指定发送消息
	 * <p>TODO</p>
	 * @param content 内容
	 * @param title   标题
	 * @param accountId 用户id
	 * @throws Exception
	 * @author lkd
	 */
	public static void sendMessageToAndroid(String content, String title, Integer accountId) throws Exception {
		PushPayload payLoad = PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.alias(String.valueOf(accountId)))
				.setNotification(Notification.android(content, title, null)).build();
		JPushUtils.getJpushClient().sendPush(payLoad);
	}

	/**
	 * 给所有平台 所有用户 发通知
	 * <p>TODO</p>
	 * @param content 内容
	 * @throws Exception
	 * @author lkd
	 */
	public static void sendMessageToAll(String content) throws Exception {
		PushPayload payLoad = PushPayload.alertAll(content);
		JPushUtils.getJpushClient().sendPush(payLoad);
	}

	/**
	 * 发送至=====所有平台===== 用户accountId指定发送消息
	 * <p>TODO</p>
	 * @param content 内容
	 * @param title   标题
	 * @param accountId 用户id
	 * @throws Exception
	 * @author lkd
	 */
	public static void sendMessageToALL(String title, String content, String id) {
		try {
//			PushPayload payLoad = PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.registrationId(id))
//					.setNotification(Notification.alert(content)).setOptions(Options.newBuilder().setApnsProduction(false).build()).build();
//			JPushUtils.getJpushClient().sendPush(payLoad);
			PushPayload payLoad =	PushPayload.newBuilder()
            .setPlatform(Platform.all())
            .setAudience(Audience.registrationId(id))
            .setNotification(Notification.newBuilder()
                    .addPlatformNotification(IosNotification.newBuilder()
                            .setAlert(content)
                            .setBadge(5)
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

	
	public static void main(String[] args) throws Exception {
		// JPushUtils.sendMessageToAndroidAndIos("标题-----------", "内容------------", 297);
		// JPushUtils.sendMessageToIos("标题--(个人推送)", "内容--(xxxx)", 304);
		// JPushUtils.sendMessageToAll("再来一遍？");
			
			JPushUtils.sendMessageToALL("title", "fdsfsdfdsfdsfsdfdsf", "101d85590944c0b4593");
	}
}
