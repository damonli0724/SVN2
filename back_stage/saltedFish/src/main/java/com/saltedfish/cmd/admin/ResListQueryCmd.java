/*******************************************************************************
 * Project   : saltedFish
 * Class Name: com.saltedfish.cmd.admin.ResListQueryCmd
 * Created By: LKD 
 * Created on: 2016年1月21日 下午5:22:51
 * Copyright © 2013-2014 saltedFish All rights reserved.
 ******************************************************************************/
package com.saltedfish.cmd.admin;

import com.saltedfish.cmd.base.BaseCmd;


/**
 * <P>资源列表Cmd</P>
 * @author LKD
 */
public class ResListQueryCmd extends BaseCmd {
	String resName; // 资源名称

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	@Override
	public String toString() {
		return "ResListQueryCmd [resName=" + resName + "]";
	}

}
