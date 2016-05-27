package com.saltedfish.service.dwr;

import org.directwebremoting.impl.DefaultScriptSessionManager;

public class DWRScriptSessionManager extends DefaultScriptSessionManager{
	//scriptSessionManager管理者 添加 DWRScriptSessionListener 监听
	public DWRScriptSessionManager() {
		this.addScriptSessionListener( new DWRScriptSessionListener());
	}
}
	