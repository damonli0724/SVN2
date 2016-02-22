/*******************************************************************************
 * Project   : saltedFish
 * Class Name: com.saltedfish.dto.security.ResourceListDTO
 * Created By: LKD 
 * Created on: 2016年1月21日 下午5:04:29
 * Copyright © 2013-2014 saltedFish All rights reserved.
 ******************************************************************************/
package com.saltedfish.dto.security;

import com.saltedfish.entity.security.SysResources;


/**
 * <P>资源列表DTO</P>
 * @author lkd
 */
public class ResourceListDTO extends SysResources {
	private static final long serialVersionUID = 1L;

	private String pName;   // 父级别名称

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

}
